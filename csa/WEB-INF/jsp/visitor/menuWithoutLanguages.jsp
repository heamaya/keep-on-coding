	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	 
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
	<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<table id="menuTable">
		<tr>	
			<td>
				<a id="startMenu" tabindex="0" href="#startMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					<s:text name="start" />
				</a>
				<div id="startMenuItems" class="hidden menu">
					<ul>
						<li>
							<s:a href="%{homePresentationActionUrl}"><s:text name="presentation" /></s:a>
						</li>
						<li>
							<s:a href="%{newsPresentationActionUrl}"><s:text name="news" /></s:a>
						</li>
						<li>
							<s:a href="%{videosPresentationActionUrl}"><s:text name="videos" /></s:a>
						</li>
						<li>
							<s:a href="%{loginActionUrl}"><s:text name="login" /></s:a>
						</li>
						<li>
							<s:a href="#"><s:text name="professionals" /></s:a>
							<ul>
								<li>
									<s:a href="%{whatDoWeDoPresentationActionUrl}"><s:text name="whatDoWeDo" /></s:a>
								</li>
								<li>
									<s:a href="%{whoAreWePresentationActionUrl}"><s:text name="whoAreWe" /></s:a>
								</li>
								<li>
									<s:a href="%{contactUsPresentationActionUrl}"><s:text name="contactUs"></s:text></s:a>
								</li>
							</ul>
						</li>
					</ul>	
				</div>	
			</td>
			<td>
				<a id="technologiesMenu" tabindex="0" href="#technologiesMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					<s:text name="technologies" />
				</a>
				<div id="technologiesMenuItems" class="hidden menu">
					<ul>
						<li>
							<s:a href="%{terracePresentationActionUrl}"><s:text name="terraces" /></s:a>
						</li>
						<li>
							<s:a href="%{dikePresentationActionUrl}"><s:text name="dikes" /></s:a>
						</li>
						<li>
							<s:a href="%{channelPresentationActionUrl}"><s:text name="channels" /></s:a>
						</li>
						<li>
							<s:a href="%{gullyRecoveryPresentationActionUrl}"><s:text name="gullyRecovery" /></s:a>
						</li>
					</ul>
				</div>
			</td>
			<td>
				<a id="farmersMenu" tabindex="0" href="#farmersMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					<s:text name="conservationistFarmers" />
				</a>
				<div id="farmersMenuItems" class="hidden menu">
					<ul>
						<li>
							<s:a href="%{consortiumPresentationActionUrl}"><s:text name="consortia" /></s:a>
						</li>
						<li>
							<s:a href="%{systematizationPresentationActionUrl}"><s:text name="starredWorks" /></s:a>
						</li>
					</ul>
				</div>
			</td>
			<td>
				<a id="helpMenu" tabindex="0" href="#helpMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					<s:text name="help" />
				</a>
				<div id="helpMenuItems" class="hidden menu">
					<ul>
						<li>
							<s:a href="%{aboutWebApplicationPresentationActionUrl}"><s:text name="aboutWebApplication" /></s:a>
						</li>
					</ul>
				</div>
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
		$(
			function() {
		    	$('.fg-button').hover(
					function(){ $(this).removeClass('ui-state-default').addClass('ui-state-focus'); },
				    function(){ $(this).removeClass('ui-state-focus').addClass('ui-state-default'); }
				 );
				    	 	
		    	if($.browser.msie == true) {
					$('#startMenu').menu({ 
						content: $('#startMenu').next().html(),
						showSpeed: 200,
						crossSpeed: 200, 
						backLink:true
					});		    				    		
		    	} else {
					$('#startMenu').menu({ 
						content: $('#startMenu').next().html(),
						showSpeed: 400,
						flyOut: true
					});		    		
		    	}
				
				$('#technologiesMenu').menu({ 
					content: $('#technologiesMenu').next().html(),
					showSpeed: 400,
					flyOut: true
				});
				
				
				$('#farmersMenu').menu({ 
					content: $('#farmersMenu').next().html(),
					showSpeed: 400,
					flyOut: true
				});
				
				$('#helpMenu').menu({ 
					content: $('#helpMenu').next().html(),
					showSpeed: 400,
					flyOut: true
				});
			}
		);
	</script>