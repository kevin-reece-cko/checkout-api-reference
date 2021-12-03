(function(h,o,u,n,d) {
  h=h[d]=h[d]||{q:[],onReady:function(c){h.q.push(c)}}
  d=o.createElement(u);d.async=1;d.src=n
  n=o.getElementsByTagName(u)[0];n.parentNode.insertBefore(d,n)
})(window,document,'script','https://www.datadoghq-browser-agent.com/datadog-logs-v3.js','DD_LOGS')
DD_LOGS.onReady(function() {
    DD_LOGS.init({
      clientToken: 'pub1378db53f51e4131b668bec6d3a4c946',
      site: 'datadoghq.com',
      service: 'api-reference',
      forwardErrorsToLogs: true,
      sampleRate: 100,
    })
  })
