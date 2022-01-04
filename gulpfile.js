var connect = require('connect');
var cors = require('cors');
var { existsSync, mkdirSync } = require('fs');
const gulp = require('gulp');
var { watch } = require('gulp');
var gulpConnect = require('gulp-connect');
var exec = require('gulp-exec');
var util = require('gulp-util');
var portfinder = require('portfinder');
var swaggerRepo = require('swagger-repo');

var DIST_DIR = 'web_deploy';

if (!existsSync(DIST_DIR)) {
	mkdirSync(DIST_DIR);
}

gulp.task('build', () => gulp.src(DIST_DIR).pipe(exec('npm run build:all')));

gulp.task(
	'reload',
	gulp.series('build', () => gulp.src(DIST_DIR).pipe(gulpConnect.reload()))
);

gulp.task('watch', (cb) => {
	watch('abc_spec/**/*', gulp.series('reload'));
	watch('nas_spec/**/*', gulp.series('reload'));
	watch('web/**/*', gulp.series('reload'));
	cb();
});

gulp.task('edit', () => {
	return portfinder
		.getPortPromise({ port: 5000 })
		.then((port) => {
			var app = connect();
			app.use(swaggerRepo.swaggerEditorMiddleware());
			app.listen(port);
			util.log(util.colors.green('swagger-editor started http://localhost:' + port));
		})
		.catch((err) => {
			// Could not get a free port, `err` contains the reason.
			console.log(err);
		});
});

gulp.task('start_site', (cb) => {
	return portfinder
		.getPortPromise({ port: 3001 })
		.then((port) => {
			// `port` is guaranteed to be a free port in this scope.
			gulpConnect.server(
				{
					root: [DIST_DIR],
					livereload: true,
					port: port,
					middleware: (_, __) => {
						return [cors()];
					},
				},
				function () {
					this.server.on('close', cb);
				}
			);
		})
		.catch((err) => {
			// Could not get a free port, `err` contains the reason.
			console.log(err);
		});
});

gulp.task('serve', gulp.series('build', gulp.parallel('watch', 'edit', 'start_site')));
