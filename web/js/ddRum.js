(function(h,o,u,n,d) {
    h=h[d]=h[d]||{q:[],onReady:function(c){h.q.push(c)}}
    d=o.createElement(u);d.async=1;d.src=n
    n=o.getElementsByTagName(u)[0];n.parentNode.insertBefore(d,n)
  })(window,document,'script','https://www.datadoghq-browser-agent.com/datadog-rum-v3.js','DD_RUM')
  DD_RUM.onReady(function() {
    DD_RUM.init({
      clientToken: 'pub1378db53f51e4131b668bec6d3a4c946',
      applicationId: '37fe93ec-a46f-4ea2-8ee1-b3a124982306',
      site: 'datadoghq.com',
      service:'api-reference',
      version: '1.0.0',
      sampleRate: 100,
      trackInteractions: true,
      defaultPrivacyLevel: 'mask-user-input'
    });
    
    DD_RUM.startSessionReplayRecording();
  })
