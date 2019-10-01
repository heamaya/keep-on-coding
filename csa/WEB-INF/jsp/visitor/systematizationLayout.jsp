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
			<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	       
	        <script type="text/javascript">
	        	var contextPath = "<%=request.getContextPath()%>";
	        	var language = "";
	        	var namespace = "/Util";
	        	var dialogTitle = "";
	        
	        	var ge;
				google.load("earth", "1");
				var currentKmlObject;
		    	var currentKmlUrl;
				
		        var systematizationsIds = null;
				var isGoogleEarthPlugin = true;
		    	
		    	function initSystematizations() {
					$.getJSON(namespace + "/SystematizationPresentationIdsJSONAction",
						function(ids) {
							systematizationsIds = new Array(ids.length);
							
							for (var i = 0; i < ids.length; i++) {
			    	    		systematizationsIds[i] = ids[i];
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
            
	                showSystematization(0);
	                showSystematizationBeforePhotos(0);
	                showSystematizationAfterPhotos(0);
	                
	                $("#currentKmlLayers").removeAttr("disabled");
	                $(".googleEarthButton").button("enable");
	            }
	
	            function failureCB(errorCode) {
	                isGoogleEarthPlugin = false;
	                
	                $("#currentKmlLayers").attr("disabled", "disabled");
	                $(".googleEarthButton").button("enable");
	                	                
	                showSystematization(0);
	                showSystematizationBeforePhotos(0);
	                showSystematizationAfterPhotos(0);
	            }
	            
			    function showSystematization(index) {			
			    	$(document).unbind("keydown");
			    	
					$.getJSON(namespace + "/SystematizationPresentationJSONAction",
				    	{systematizationId:systematizationsIds[index]},
				    	function(systematization) {
				    		$("#companyName").html(systematization.company.name);
				    		$("#landName").html(systematization.land.name);
				    		
				    		if(language == "es") {
				    			$("#systematizationDescription").html('<div class=paragraph>' + systematization.descriptionSpanish + '</div>');	
				    		} else if(language == "pt") {
				    			$("#systematizationDescription").html('<div class=paragraph>' + systematization.descriptionPortuguese + '</div>');
				    		} else {
				    			$("#systematizationDescription").html('<div class=paragraph>' + systematization.descriptionEnglish + '</div>');
				    		}
				    		  		
				    		if(isGoogleEarthPlugin == true) {
				    		
					    		$("#currentKmlName").html(systematization.systematizationFileName);
					    		currentKmlUrl = contextPath + namespace + "/SystematizationDownloadFileAction_getFile?" + 
				    				$.param({"fileName":systematization.systematizationFileName,"contentType":systematization.systematizationContentType,"systematizationId":systematization.id});
					    		
				    			if (currentKmlObject) {
					    			ge.getFeatures().removeChild(currentKmlObject);
					    			currentKmlObject = null;
					    		}
				    			
					    		var link = ge.createLink('');
					    		var href = window.location.protocol + "//" + window.location.host + currentKmlUrl;
				    			link.setHref(href);	
					    		currentKmlObject = ge.createNetworkLink('');
					    		currentKmlObject.set(link, true, true); // Sets the link, refreshVisibility, and flyToView.
		
				     			ge.getFeatures().appendChild(currentKmlObject);
				    		}
				    	}
				    );
			    }
			    
			    function showSystematizationBeforePhotos(index) {
			    	
			    	$.getJSON(namespace + "/SystematizationBeforePhotosJSONAction",
					    	{systematizationId:systematizationsIds[index]},
					    	function(systematizationPhotos) {
					    							    		
					    		if(systematizationPhotos.length > 0) {
					    			$("#beforeThumbnailsPane").dialog("destroy");
					    			$("#beforeThumbnailsPane").replaceWith('<div id="beforeThumbnailsPane"></div>');
					    			
					    			var photosHtml = "<ul>";
					    				
					    			for(var i = 0; i < systematizationPhotos.length; i++) {
					    				var currentPhotoUrl = contextPath + namespace + "/SystematizationBeforePhotoDownloadFileAction_getFile?" +
						    			$.param({"fileName":systematizationPhotos[i].systematizationPhotoFileName,"contentType":systematizationPhotos[i].systematizationPhotoContentType,"systematizationId":systematizationsIds[index]});
					    				
					    				var description = "";
					    				
							    		if(language == "es") {
							    			description = systematizationPhotos[i].descriptionSpanish;	
							    		} else if(language == "pt") {
							    			description = systematizationPhotos[i].descriptionPortuguese;
							    		} else {
							    			description = systematizationPhotos[i].descriptionEnglish;
							    		}
					    				
					    				photosHtml = photosHtml + 
					    					'<li>' + 
					    					'<img src="' +
					    					currentPhotoUrl +
					    					'" width="96" height="72" class="ui-corner-all" title="' +
					    					description + 
					    					'" /></li>';
					    			}
					    				
					    			photosHtml = photosHtml + "</ul>";
					    			
					    			/*
					    			$("#systematizationImages").remove("#beforePhotoPane");
					    			('<div id="beforePhotoPane">')
				    				.append('<img id="beforePhotoDisplay" src=""/>')
				    				.append('<div id="beforePhotoDescription"></div>')
				    				.append('<div id="beforeCurrentPhoto"></div>')
				    				.appendTo("#systematizationImages");
					    			*/
					    			$("#beforePhotoPane").dialog("destroy");
					    			$("#beforeThumbnailsPane").empty().html(photosHtml);
						        	$("#beforeThumbnailsPane img").rushSlideShow({
						        		hasDialog:true,
									    photoPane: '#beforePhotoPane',
										photoElement: '#beforePhotoDisplay',
										photoDescription: '#beforePhotoDescription',
										currentPhoto: '#beforeCurrentPhoto',
										playButton:'#beforePlayButton',
										previousButton:'#beforePreviousButton',
										firstButton:'#beforeFirstButton',
										nextButton:'#beforeNextButton',
										lastButton:'#beforeLastButton',
										helpButton:'#beforeHelpButton',
										closeButton:'#beforeCloseButton',
						        		dialogTitle:dialogTitle,
						        		language:language
									});
					    				
					    		} else {
					    			$("#beforePhotoPane").dialog("destroy");
					    			$("#beforeThumbnailsPane").replaceWith('<div id="beforeThumbnailsPane"></div>');
					    		}
					    	}
					    );
			    }
			    
			    function showSystematizationAfterPhotos(index) {
			    	
			    	$.getJSON(namespace + "/SystematizationAfterPhotosJSONAction",
					    	{systematizationId:systematizationsIds[index]},
					    	function(systematizationPhotos) {
			    	
			    				if(systematizationPhotos.length > 0) {
		    						$("#afterThumbnailsPane").dialog("destroy");
		    						$("#afterThumbnailsPane").replaceWith('<div id="afterThumbnailsPane"></div>');
		    			
		    						var photosHtml = "<ul>";
		    				
		    						for(var i = 0; i < systematizationPhotos.length; i++) {
		    							var currentPhotoUrl =  contextPath + namespace + "/SystematizationAfterPhotoDownloadFileAction_getFile?" + 
			    						$.param({"fileName":systematizationPhotos[i].systematizationPhotoFileName,"contentType":systematizationPhotos[i].systematizationPhotoContentType,"systematizationId":systematizationsIds[index]});
		    							
					    				var description = "";
					    				
							    		if(language == "es") {
							    			description = systematizationPhotos[i].descriptionSpanish;	
							    		} else if(language == "pt") {
							    			description = systematizationPhotos[i].descriptionPortuguese;
							    		} else {
							    			description = systematizationPhotos[i].descriptionEnglish;
							    		}

		    							photosHtml = photosHtml + 
		    							"<li>" + 
		    							'<img src="' +
		    							currentPhotoUrl +
		    							'" width="96" height="72" class="ui-corner-all" title="' + 
		    							description + 
		    							'" /></li>';
		    						}
		    				
		    						photosHtml = photosHtml + "</ul>";
		    				
		    						$("#afterPhotoPane").dialog("destroy");
		    						$("#afterThumbnailsPane").empty().html(photosHtml);
			        				$("#afterThumbnailsPane img").rushSlideShow({
			        					hasDialog:true,
						    			photoPane: '#afterPhotoPane',
										photoElement: '#afterPhotoDisplay',
										photoDescription: '#afterPhotoDescription',
										currentPhoto: '#afterCurrentPhoto',
										playButton:'#afterPlayButton',
										previousButton:'#afterPreviousButton',
										firstButton:'#afterFirstButton',
										nextButton:'#afterNextButton',
										lastButton:'#afterLastButton',
										helpButton:'#afterHelpButton',
										closeButton:'#afterCloseButton',
						        		dialogTitle:dialogTitle,
						        		language:language
									});
		    				
					    		} else {
		    						$("#afterPhotoPane").dialog("destroy");
		    						$("#afterThumbnailsPane").replaceWith('<div id="afterThumbnailsPane"></div>');
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
			    		    lang: language 
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
