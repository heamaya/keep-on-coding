<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>

<html>
	<head>
		<title><t:insertAttribute name="title" /></title>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
		<sj:head jqueryui="true" jquerytheme="south-street" loadAtOnce="true" locale="es"/>
		<s:head />
		<link rel="stylesheet" type="text/css" href='<s:url value="/css/csa.css" />' />
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/fg-menu/fg.menu.css" />' media="screen"/>
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/fileinput/fileinput.css" />' />
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/jquery.ui.selectmenu.css" />' media="screen" />
		<link rel="stylesheet" type="text/css" href='<s:url value="/js/kmltree.css" />' media="screen"/>
		
		<style type="text/css">
			.ui-widget-overlay { 
				background:#459e00; 
				url(images/ui-bg_flat_0_09299f_40x100.png) 50% 50% repeat-x;
				opacity: .30;
				filter:Alpha(Opacity=30);
			}
			
			.ui-widget-shadow { 
				margin: -8px 0 0 -8px;
				padding: 8px;
				background: #459e00; 
				url(images/ui-bg_flat_0_0d5ef8_40x100.png) 50% 50% repeat-x; 
				opacity: .30;
				filter:Alpha(Opacity=30);
			}
			
			.ui-dialog-title {
  				float:none !important;
  				display: block;
  				text-align: center;
  				font-family: segoe ui, Arial, sans-serif;
  				font-size: small;
			}

			.ui-dialog .ui-dialog-buttonpane button { 
				margin: .5em .4em .5em 0;
				cursor: pointer;
				font-size: x-small;
				font-family: segoe ui, Arial, sans-serif; 
			}
			
			.ui-datepicker { width: 17em; padding: .2em .2em 0; font-size: x-small; }
		</style>
		
 		<script type="text/javascript" src='<s:url value="/js/jquery.form.js" />'></script>
 		<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/jquery.validate.js" />'></script>
 		<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/localization/messages_es.js" />'></script>
		<script type="text/javascript" src='<s:url value="/js/fg-menu/fg.menu.js" />' ></script>
		<script type="text/javascript" src='<s:url value="/js/jquery-ui-dialog-ckeditor-patch.js" />' ></script>
		<script type="text/javascript" src='<s:url value="/js/fileinput/jquery.fileinput.js" />'></script>
		<script type="text/javascript" src='<s:url value="/js/jquery.ui.selectmenu.js" />' ></script>
		
		<script type="text/javascript" src='<s:url value="/js/extensions-0.2.1.js" />'></script>
		<script type="text/javascript" src='<s:url value="/js/geo-0.2.js" />' ></script>
		<script type="text/javascript" src='<s:url value="/js/kmltree.js" />' ></script>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		
		<script type="text/javascript">
			var IE7_PNG_SUFFIX = ".png";
			var farmersData = null;
			
		    $(	
		    	function() {
			    	$.validator.setDefaults({ 
			    		   lang: "es" 
			    	});
		    		
		        	$(".listButton").live("click",
		        		function(event) {
		        	  		$("#ajaxLoader").html('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
		          		}	  
		        	);
		          
		        	$("#currentDialog").dialog("option", "stack", false);
	  	  		}	
		    );
		
	  		$.subscribe('closeCurrentDialog', function(event, data) {
		    	$('#currentDialog').dialog('close');
		    });
		    
		    $.subscribe('beforeOpenCurrentDialog', function(event, data) {
		    	$('#currentDialog.ui-dialog-titlebar-close').bind('click', 
		    		function(event) {
		    			$('#currentDialog').dialog('close');
		    		}
		    	);
		    });
		    
		    $.subscribe('openCurrentDialog', function(event, data) {
		    	$("#ajaxLoader").empty();
		    	$('#currentDialog').dialog('open');
		    });
		    
		    $.subscribe('completeRefresh', function(event, data) {
		    	$("#ajaxLoader").empty();
		    });
  			
		    var contextPath = "<%=request.getContextPath()%>";
        	var language = "es";
        	var namespace = "";
        	var dialogTitle = "";
        
        	
			google.load("earth", "1");
			var ge;
			var isGoogleEarthPlugin = true;
			var currentKmlObject = null;
			var currentKmlHref = null;
			var firstKml = null;

            function init() {
            	google.earth.setLanguage(language);
                google.earth.createInstance('map3d', initCB, failureCB);
            }

            function initCB(instance) {
            	ge = instance;
            	var gex = new GEarthExtensions(ge);
            	ge.getOptions().setUnitsFeetMiles(false);
            	
            	
            	if($.browser.msie) {
            		//DISPLAY OPTIONS
            		$('<br /><span>Opciones de Pantalla</span><br /><br />')
            		.addClass('googleEarthOptionTitle')
            		.appendTo('#displayOptionsTree');
      
            		$('<input type="checkbox">')
        			.attr("id", "navigationControls")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setNavigationControls(true);	
        					} else {
        						setNavigationControls(false);	
        					}
        				}
        			)
        			.appendTo('#displayOptionsTree');
            		
            		$('<span>Controles de Navegación</span><br />')
            		.addClass('googleEarthOption')
            		.appendTo('#displayOptionsTree');
	            		
            		$('<input type="checkbox">')
        			.attr("id", "historicalImagery")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setHistoricalImagery(true);	
        					} else {
        						setHistoricalImagery(false);	
        					}
        				}
        			)
        			.appendTo('#displayOptionsTree');
            		
            		$('<span>Imágenes Históricas</span><br />')
            		.addClass('googleEarthOption')
            		.appendTo('#displayOptionsTree');
            		
					$('<input type="checkbox">')
        			.attr("id", "statusBar")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setStatusBar(true);	
        					} else {
        						setStatusBar(false);	
        					}
        				}
        			)
        			.appendTo('#displayOptionsTree');
					
            		$('<span>Barra de Estado</span><br />')
            		.addClass('googleEarthOption')
            		.appendTo('#displayOptionsTree');
					
            		$('<input type="checkbox">')
        			.attr("id", "scale")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setScale(true);	
        					} else {
        						setScale(false);	
        					}
        				}
        			)
        			.appendTo('#displayOptionsTree');
            		
            		$('<span>Escala</span><br />')
            		.addClass('googleEarthOption')
            		.appendTo('#displayOptionsTree');
            		
            		//LAYERS
            		$('<br /><span>Capas Google Earth</span><br /><br />')
            		.addClass("googleEarthLayerTitle")
            		.appendTo('#layersTree');
            		
            		$('<input type="checkbox">')
        			.attr("id", "roads")
        			.addClass("googleEarthOption")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setRoads(true);	
        					} else {
        						setRoads(false);	
        					}
        				}
        			)
        			.appendTo('#layersTree');
            		
            		$('<span>Rutas y Caminos</span><br />')
            		.addClass('googleEarthLayer')
            		.appendTo('#layersTree');
            			            		
            		$('<input type="checkbox">')
            		.attr("id", "borders")
            		.addClass("googleEarthLayer")
            		.click(
            			function(event) {
            			       						
            			   	if($(this).is(':checked')) {
            			       	setBorders(true);	
            			    } else {
            			       	setBorders(false);	
            			    }
            			}
            		)
            		.appendTo('#layersTree');
            		
            		$('<span>Límites Geográficos</span><br />')
            		.addClass('googleEarthLayer')
            		.appendTo('#layersTree');
            		
            		$('<input type="checkbox">')
        			.attr("id", "terrain")
        			.addClass("googleEarthLayer")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setTerrain(true);	
        					} else {
        						setTerrain(false);	
        					}
        				}
        			)
        			.appendTo('#layersTree');
            		
            		$('<span>Terreno en 3D</span><br />')
            		.addClass('googleEarthLayer')
            		.appendTo('#layersTree');
        		        			        			
            		$('<input type="checkbox">')
        			.attr("id", "trees")
        			.addClass("googleEarthLayer")
        			.click(
        				function(event) {
        						
        					if($(this).is(':checked')) {
        						setTrees(true);	
        					} else {
        						setTrees(false);	
        					}
        				}
        			)
        			.appendTo('#layersTree');
            		
            		$('<span>Árboles en 3D</span><br />')
            		.addClass('googleEarthLayer')
            		.appendTo('#layersTree');
        		
            		/*
					//DISPLAY OPTIONS
            		setNavigationControls(false);
            		setHistoricalImagery(false);
        			setStateBar(false);	
            		setScale(false);
        			//LAYERS
          			setRoads(false);
        			setBorders(false);
            		setTerrain(false);
            		setTrees(false);
            		*/
            		ge.getWindow().setVisibility(true);
            	} else {
	                
			        var displayOptionsTree = kmltree({
			          	url: "/kml/displayOptions.kml",
			           	gex: gex,
			           	mapElement: $('#map3d'),
			           	element: $('#displayOptionsTree'),
			           	loadingMsg:"Cargando KML",
			           	supportItemIcon:true
			        });
        
			        displayOptionsTree.load();
			        enableGoogleLayersControl(displayOptionsTree, ge);
			        
			        var layersTree = kmltree({
			        	url: "/kml/includedLayers.kml",
			            gex: gex,
			            mapElement: $('#map3d'),
			            element: $('#layersTree'),
			            loadingMsg:"Cargando KML",
			            supportItemIcon:true
			        });
			            
			        layersTree.load();		        
			        enableGoogleLayersControl(layersTree, ge);
			        
			        ge.getWindow().setVisibility(true);
            	}
                
            	showSystematization($(".showInGoogleEarth").first().attr("id"));
            	
                //$("#currentKmlLayers").removeAttr("disabled");
                //$(".googleEarthButton").button("enable");
            }

            function failureCB(errorCode) {
                isGoogleEarthPlugin = false;
                $("#currentKmlLayers").attr("disabled", "disabled");
                $(".showInGoogleEarth").attr("disabled", "disabled");
            }
            
		    function showSystematization(url) {					    	
			    		  		
			    if(isGoogleEarthPlugin == true) {
			    	currentKmlHref = window.location.protocol + "//" + window.location.host + contextPath + url;
			    	var featuresLength = ge.getFeatures().getChildNodes().getLength();
			    	var currentFeature = null;
			    	var isShown = false;
			    	
			    	for(var f = 0; f < featuresLength; f++) {
			    		currentFeature = ge.getFeatures().getChildNodes().item(f);
			    		
			    		if(currentFeature.getUrl() != null && currentFeature.getUrl() == currentKmlHref) {
			    			isShown = true;
			    			break;
			    		}
			    		
			    	}
			    	
	    			if(isShown) {
		    			ge.getFeatures().removeChild(currentFeature);
		    		} else {
		    		
		    		
		    			var link = ge.createLink('');
		    			link.setHref(currentKmlHref);	
			    		currentKmlObject = ge.createNetworkLink('');
			    		currentKmlObject.set(link, true, true); //Sets the link, refreshVisibility, and flyToView.
						alert(currentKmlObject);
		     			ge.getFeatures().appendChild(currentKmlObject);
			    		
			    		//alert(currentKmlHref);
			    		//google.earth.fetchKml(ge, currentKmlHref, loadKml);
		    		
		     		}
	    				    			
				}

		    }
		    
		    function loadKml(kmlObject) {
				//alert(kmlObject);
		    	
				if(kmlObject) {
			      		
			      	currentKmlObject = kmlObject;
			      	
			      	ge.getFeatures().appendChild(currentKmlObject);
					
					if (currentKmlObject.getAbstractView() != null) {
						ge.getView().setAbstractView(kmlObject.getAbstractView());
					}
				}
			}
		    
		    function turnOnCurrentKmlObject() {
		    	ge.getFeatures().appendChild(currentKmlObject);
		    }
		    
		    function turnOffCurrentKmlObject() {
		    	ge.getFeatures().removeChild(currentKmlObject);
		    }			    
		    
		    function setNavigationControls(enable) {
		    	
		    	if(enable) {
		    		ge.getNavigationControl().setVisibility(ge.VISIBILITY_SHOW);		
		    	} else {
		    		ge.getNavigationControl().setVisibility(ge.VISIBILITY_HIDE);		
		    	}
		    	
		    }
		    
		    function setHistoricalImagery(enable) {
		    	
		    	if(enable) {
		    		ge.getTime().getControl().setVisibility(ge.VISIBILITY_SHOW);		
		    	} else {
		    		ge.getTime().getControl().setVisibility(ge.VISIBILITY_HIDE);		
		    	}
		    	
		    }
    
		    function setStatusBar(enable) {
		    	ge.getOptions().setStatusBarVisibility(enable);
		    }
		    
		    function setScale(enable) {
		    	ge.getOptions().setScaleLegendVisibility(enable);
		    }
		    
		    function setRoads(enable) {
	    		ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, enable);
		    }
		    
		    function setBorders(enable) {
	    		ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, enable);
		    }
		    
		    function setTerrain(enable) {
	    		ge.getLayerRoot().enableLayerById(ge.LAYER_TERRAIN, enable);
		    }
		    
		    function setTrees(enable) {
	    		ge.getLayerRoot().enableLayerById(ge.LAYER_TREES, enable);
		    }
	    
		    $(
		    	function() {
		    		$.validator.setDefaults({ 
		    		    lang: language 
		    		});
		    	}
		    );
		    
		    
		</script>
	</head>
	<body>
		<table class="layout">
			<tr>
				<td class="border ui-corner-all">
					&nbsp;
				</td>
				<td>
					<table class="center">
						<tr>
							<td class="header ui-corner-all" >
								<t:insertAttribute name="header" />		
							</td>
						</tr>
						<tr>
							<td class="menu">
								<t:insertAttribute name="menu" />	
							</td>
						</tr>
						<tr>
							<td class="content">
								<t:insertAttribute name="content" />	
							</td>
						</tr>
						<tr>
							<td class="footer ui-corner-all">
								<t:insertAttribute name="footer" />			
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>