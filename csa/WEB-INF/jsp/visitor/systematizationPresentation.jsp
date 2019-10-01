	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>

	<h1 class="title">
		<s:text name="starredWorks" />
	</h1>

	<table id="systematizationButtonsPane">
		<tr>
			<td id="firstGEButton" class="googleEarthButton"></td>
			<td id="previousGEButton" class="googleEarthButton"></td>
			<td id="nextGEButton" class="googleEarthButton"></td>
			<td id="lastGEButton" class="googleEarthButton"></td>
		</tr>
	</table>
	 	
	<div class="systematizationTitle">  
	    
		<div class="title">
			<s:text name="farmCompany" />: <span id="companyName"></span> 
		</div>
		
		<div class="title">				
			<s:text name="farm" />: <span id="landName"></span>
		</div>
	</div>
		
	<sj:tabbedpanel id="systematizationTabbedPanel" animate="true" collapsible="false" useSelectedTabCookie="false" >
		<sj:tab id="mapTab" target="systematizationMap" label="%{getText('map')}" />
		<sj:tab id="imagesTab"  target="systematizationImages" label="%{getText('images')}" /> 
		<sj:tab id="descriptionTab" target="systematizationDescription" label="%{getText('description')}" />
		
      	<div id="systematizationMap">

			<div class="googleEarthContentPane" id="googleEarthContentPane">
				<div class="googleEarthTree">
					<div id="kmlTree" class="googleEarthKmlTree">
						<input type="checkbox" id="currentKmlLayers" checked="checked"><span id="currentKmlName"></span> 
					</div>
					<div id="displayOptionsTree" class="googleEarthKmlTree"></div>
					<div id="layersTree" class="googleEarthKmlTree"></div>
				</div>
				<div id="map3d" class="googleEarthMap"></div> 
			</div>
      	</div>
      	
      	<div id="systematizationImages">
      		<div id="beforeGallery" class="ui-corner-all gallery" >
				<div id="beforeThumbnailsPaneHeader">
					<s:text name="previousImages" />
				</div>
				<div id="beforeThumbnailsPane">
	    		</div>
			</div>
		
			<div id="afterGallery" class="ui-corner-all gallery" >
				<div id="afterThumbnailsPaneHeader">
					<s:text name="afterwardsImages" />
				</div>
				<div id="afterThumbnailsPane">
			    </div>
			</div>
		
		    <div id="beforePhotoPane">
		    	<img id="beforePhotoDisplay" src=""/>
		    	<div id="beforePhotoDescription"></div>
		    	<div id="beforeCurrentPhoto"></div>
		    </div>
		    
		    <div id="afterPhotoPane">
		    	<img id="afterPhotoDisplay" src=""/>
		    	<div id="afterPhotoDescription"></div>
		    	<div id="afterCurrentPhoto"></div>
		    </div>
      	</div>

      	<div id="systematizationDescription" class="googleEarthDescription"></div>
    </sj:tabbedpanel>

	<script type="text/javascript">
		$("#systematizationTabbedPanel").hide();
		$(
			function() {
				var currentSystematizationIndex = 0;
				var next = "";
				var	previous = "";
				var first = "";
				var last = "";
				var googleEarthIE6 = "";
				
				language = '<s:property value="%{#language}"/>';
					
				if(language == "es") {
					next = 'Siguiente';
					previous = 'Previo';
					first = 'Primero';
					last = 'Último';
					googleEarthIE6 = "El complemento de Google Earth funciona en Internet Explorer 6, de todos modos te recomendamos que actualices tu versión de Internet Explorer o que uses Firefox, Chrome u Opera.";
					dialogTitle = "Fotos de la Sistematización";
				} else if(language == "pt") {
					next = 'Próxima';
					previous = 'Anterior';
					first = 'Primeiro';
					last = 'Último';
					googleEarthIE6 = "A adição do Google Earth funciona no Internet Explorer 6, no entanto, recomendamos que atualize a sua versão do Internet Explorer ou que use o Firefox , Chrome ou Opera.";
					dialogTitle = "Fotos da Sistematização";
				} else {
					next = 'Next';
					previous = 'Previous';
					first = 'First';
					last = 'Last';
					googleEarthIE6 = "The Google Earth Plugin works in Internet Explorer 6. Nevertheless, we recommend you to upgrade to a new version or to use Firefox, Chrome or Opera";
					dialogTitle = "Photos of the Systematization";
				}
				
				$("#beforePhotoPane, #afterPhotoPane").hide();
			
				$("#firstGEButton").button(
				{
					label: first,
					icons: { primary: 'ui-icon-seek-first' }
				}
				).click(
					function() {
						currentSystematizationIndex = 0;
						showSystematization(currentSystematizationIndex);
		             	showSystematizationBeforePhotos(currentSystematizationIndex);
		            	showSystematizationAfterPhotos(currentSystematizationIndex);
					}
				);
						
				$("#previousGEButton").button(
				{
					label: previous,
					icons: { primary: 'ui-icon-seek-prev' }
				}
				).click(
					function() {
						currentSystematizationIndex = (currentSystematizationIndex - 1 + systematizationsIds.length) % systematizationsIds.length;
						showSystematization(currentSystematizationIndex);
		                showSystematizationBeforePhotos(currentSystematizationIndex);
		                showSystematizationAfterPhotos(currentSystematizationIndex);
					}	
				);
						
				$("#nextGEButton").button(
				{
					label: next,
					icons: { secondary: 'ui-icon-seek-next' }
				}
				).click(
					function() {
						currentSystematizationIndex = (currentSystematizationIndex + 1 + systematizationsIds.length) % systematizationsIds.length;
						showSystematization(currentSystematizationIndex);
						showSystematizationBeforePhotos(currentSystematizationIndex);
		                showSystematizationAfterPhotos(currentSystematizationIndex);
					}
				);
						
				$("#lastGEButton").button(
				{
					label: last,
		       		icons: { secondary: 'ui-icon-seek-end' }
				}
				).click(
					function() {
						var currentSystematizationIndex = systematizationsIds.length - 1;
						showSystematization(currentSystematizationIndex);
						showSystematizationBeforePhotos(currentSystematizationIndex);
                		showSystematizationAfterPhotos(currentSystematizationIndex);
		    		}
				);
			
				$(".googleEarthButton").button("disable");
				$("#currentKmlLayers").attr("disabled", "disabled");
			
				$("#currentKmlLayers").click(
					function(event) {
					
						if($(this).is(':checked')) {
							turnOnCurrentKmlObject();	
						} else {
							turnOffCurrentKmlObject();	
						}
					}
				);	
				
				if($.browser.msie && ($.browser.version).substring(0, 1) == "6") {
			
					$("div.systematizationTitle").prepend(					
						'<div class="ui-widget">' +
	  						'<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">' +
	  							'<p class="paragraph"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>' +
								'<strong>' + googleEarthIE6 + '</strong></p>' +
								'</div>' +
  							'</div>'
					);
				}
						
				initSystematizations();
				$("#systematizationTabbedPanel").show();
			}
		);
	</script>