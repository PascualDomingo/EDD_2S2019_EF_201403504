ar chart_config = { 
chart: { 
 container: "#basic-example", 
connectors: { 
 type: 'bCurve' 
 }, 
animateOnInit: true, 
 node: { 
 collapsable: true,HTMLclass: 'nodeExample1' 
 }, 
animation: { 
 nodeAnimation: "easeOutBounce", 
 nodeSpeed: 700, 
connectorsAnimation: "bounce", 
 connectorsSpeed: 700 
 	} 
 },

nodeStructure: {  
	text: {
		name: " pasco", 
		title: "5", 
	}, 
 	collapsed: true, 
	children: [ 
		{ 
			text: {
				name: " karen", 
				title: "3", 
		}, 
 		collapsed: true, 
		children: [ 
				{ 
					text: {
						name: " ramirez", 
						title: "1", 
					}
				} 
			] 
		} 
	] 
};