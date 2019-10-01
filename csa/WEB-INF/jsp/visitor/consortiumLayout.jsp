	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %> 
	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
	
	<html>
		<head>
			<title><t:insertAttribute name="title" /></title>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" > 
			<meta name="description" content='<t:getAsString name="description" />' >
			
			<sj:head jqueryui="true" jquerytheme="south-street" loadAtOnce="true" locale="es" />
			<link rel="stylesheet" type="text/css" href='<s:url value="/css/csa.css" />' />
			<link rel="stylesheet" type="text/css" href='<s:url value="/js/fg-menu/fg.menu.css" />' media="screen"/>
			<link rel="stylesheet" type="text/css" href='<s:url value="/js/kmltree.css" />' media="screen"/>
			
			<style type="text/css">
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
			</style>
	
	 		<script type="text/javascript" src='<s:url value="/js/jquery.form.js" />' type="text/javascript"></script>
	 		<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/jquery.validate.js" />' type="text/javascript"></script>
	 		<script type="text/javascript" src='<s:url value="/js/jquery-validation-1.8.1/localization/messages_es.js" />' type="text/javascript"></script>
			<script type="text/javascript" src='<s:url value="/js/fg-menu/fg.menu.js" />' ></script>
			<script type="text/javascript" src='<s:url value="/js/rush.slide.show-0.1.js" />' ></script>
			
			<script type="text/javascript" src='<s:url value="/js/extensions-0.2.1.js" />'></script>
			<script type="text/javascript" src='<s:url value="/js/geo-0.2.js" />' ></script>
			<script type="text/javascript" src='<s:url value="/js/kmltree.js" />' ></script>
			<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAA8ZFKjN-99XcuCoHMAwn12RRk1sbbzhS8KJ1oGrTvXJpABgDXYxRwV0v724DXsPN9gaO7Ht4xJVzaIg"></script>
	       
	        <script type="text/javascript">
		        var contextPath = "<%=request.getContextPath()%>";
		        var language = "";
		        var namespace = "/Util";
            	var dialogTitle = "";
	        
		        var ge;
				google.load("earth", "1");		        
				var currentKmlObject;
		    	var currentKmlUrl;
				
		        var consortiumsIds = null;
				var isGoogleEarthPlugin = true;
				    	
		    	function initConsortiums() {
		    		
					$.getJSON(namespace + "/ConsortiumPresentationIdsJSONAction",
						function(ids) {
							consortiumsIds = new Array(ids.length);
							
							for (var i = 0; i < ids.length; i++) {
			    	    		consortiumsIds[i] = ids[i];
			    			}
			    			
			    			init();	
						}
					);
					
		    	}
		    	
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
	        		
	            		ge.getWindow().setVisibility(true);
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

	            	showConsortium(0);
	            	showConsortiumPhotos(0);
	            	
	                $("#currentKmlLayers").removeAttr("disabled");
	                $(".googleEarthButton").button("enable");
	            }
	
	            function failureCB(errorCode) {
	                isGoogleEarthPlugin = false;

	            	$("#currentKmlLayers").attr("disabled", "disabled");
		    		$(".googleEarthButton").button("enable");
		    		
	            	showConsortium(0);
	            	showConsortiumPhotos(0);
	            }
	             
			    function showConsortium(index) {
			    	$(document).unbind("keydown");
			    	
				    $.getJSON(namespace + "/ConsortiumPresentationJSONAction",
				    	{consortiumId:consortiumsIds[index]},
				    	function(consortium) {
				    		$("#name").html(consortium.name);
				    		$("#creationDate").html(consortium.creationDate);
				    		
				    		if(language == "es") {
				    			$("#consortiumDescription").html('<div class=paragraph>' + consortium.descriptionSpanish + '</div>');	
				    		} else if(language == "pt") {
				    			$("#consortiumDescription").html('<div class=paragraph>' + consortium.descriptionPortuguese + '</div>');
				    		} else {
				    			$("#consortiumDescription").html('<div class=paragraph>' + consortium.descriptionEnglish + '</div>');
				    		}
	
				    		if(isGoogleEarthPlugin == true) {

					    		$("#currentKmlName").html(consortium.limitFileName);
					    		currentKmlUrl = contextPath + namespace + "/ConsortiumDownloadFileAction_getFile?" + 
				    				$.param({"fileName":consortium.limitFileName,"contentType":consortium.limitContentType,"consortiumId":consortium.id});
		
					    		if (currentKmlObject) {
					    			ge.getFeatures().removeChild(currentKmlObject);
					    			currentKmlObject = null;
					    		}
				    			
					    		var link = ge.createLink('');
					    		var href = window.location.protocol + "//" + window.location.host + currentKmlUrl;
				    			link.setHref(href);	
					    		currentKmlObject = ge.createNetworkLink('');
					    		currentKmlObject.set(link, true, true); 
					    		//Sets the link, refreshVisibility, and flyToView.
		
				     			ge.getFeatures().appendChild(currentKmlObject);
				    		}
				    	}
				    );
	
			    }
			    
			    function showConsortiumPhotos(index) {
			    	
				    $.getJSON(namespace + "/ConsortiumPhotosJSONAction",
				    	{consortiumId:consortiumsIds[index]},
				    	function(consortiumPhotos) {

				    		if(consortiumPhotos.length > 0) {
				    			$("#thumbnailsPane").dialog("destroy");
				    			$("#thumbnailsPane").replaceWith('<div id="thumbnailsPane"></div>');
				    			
				    			var photosHtml = "<ul>";
				    				
				    			for(var i = 0; i < consortiumPhotos.length; i++) {
				    				var currentPhotoUrl = contextPath + namespace + "/ConsortiumPhotoDownloadFileAction_getFile?" + 
					    			$.param({"fileName":consortiumPhotos[i].photoFileName,"contentType":consortiumPhotos[i].photoContentType,"consortiumId":consortiumsIds[index]});
				    			
				    				var description = "";
				    				
						    		if(language == "es") {
						    			description = consortiumPhotos[i].descriptionSpanish;	
						    		} else if(language == "pt") {
						    			description = consortiumPhotos[i].descriptionPortuguese;
						    		} else {
						    			description = consortiumPhotos[i].descriptionEnglish;
						    		}
				    				
				    				photosHtml = photosHtml + 
				    					"<li>" + 
				    					'<img src="' +
				    					currentPhotoUrl +
				    					'" width="96" height="72" class="ui-corner-all" title="' +
				    					description +
				    					'"/></li>';
				    			}
				    				
				    			photosHtml = photosHtml + "</ul>";
				    			
				    			$("#photoPane").dialog("destroy");				    				
				    			$("#thumbnailsPane").empty().html(photosHtml);
					        	$("#thumbnailsPane img").rushSlideShow({
					        		hasDialog:true,
					        		dialogTitle:dialogTitle,
									language:language
								});
				    				
				    		} else {
				    			$("#photoPane").dialog("destroy");
				    			$("#thumbnailsPane").replaceWith('<div id="thumbnailsPane"></div>');
				    		}
				    	}
				    );
	
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
			    		    lang: "es" 
			    		});
			    	}	
			    );
	        </script>
		</head>
		<body>
			<s:set var="language" value="%{locale.language}" scope="action"/>
			<t:insertAttribute name="actions" />
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