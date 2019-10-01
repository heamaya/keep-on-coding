	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>

	<h1 class="title">
		<s:text name="aboutWebApplication" />
	</h1>
	<div id="aboutWebApplication" class="ui-corner-all">
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="webApplicationPurpose" /> 
		</p>
		
		<s:url id="FirefoxIconUrl" value="/icons/Firefox.jpg"/>
		<s:url id="ChromeIconUrl" value="/icons/Chrome.jpg" />
		<s:url id="OperaIconUrl" value="/icons/Opera.jpg" />
		<s:url id="ExplorerIconUrl" value="/icons/Explorer.jpg" />
		<s:url id="SafariIconUrl" value="/icons/Safari.jpg" />
		
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="webApplicationBrowsers" /> 
		</p>
		<table class="aboutWebAppIconsTable" >
			<tr>
				<td>
					<img src='<s:property value="%{#FirefoxIconUrl}" />' title='<s:text name="firefoxBrowser" />' width="60" height="56" class="ui-corner-all" />				
				</td> 
				<td>
					<img src='<s:property value="%{#ChromeIconUrl}" />' title='<s:text name="googleChromeBrowser" />' width="60" height="56" class="ui-corner-all" />				
				</td>
				<td>
					<img src='<s:property value="%{#OperaIconUrl}" />' title='<s:text name="operaBrowser" />' width="60" height="56" class="ui-corner-all" />				
				</td>
				<td>
					<img src='<s:property value="%{#ExplorerIconUrl}" />' title='<s:text name="internetExplorerBrowser" />' width="60" height="56" class="ui-corner-all" />				
				</td>
				<td>
					<img src='<s:property value="%{#SafariIconUrl}" />' title='<s:text name="safariBrowser" />' width="60" height="56" class="ui-corner-all" />				
				</td>
			</tr>
			<tr>
				<td>
					<a href='<s:text name="firefoxBrowserUrl" />' target="_blank"><s:text name="firefox" /></a>	
				</td> 
				<td>
					<a href='<s:text name="googleChromeBrowserUrl" />' target="_blank"><s:text name="googleChrome" /></a>				
				</td>
				<td>
					<a href='<s:text name="operaBrowserUrl" />' target="_blank"><s:text name="opera" /></a>				
				</td>
				<td>
					<a href='<s:text name="internetExplorerBrowserUrl" />' target="_blank"><s:text name="internetExplorer" /></a>				
				</td>
				<td>
					<a href='<s:text name="safariBrowserUrl" />' target="_blank"><s:text name="safari" /></a>				
				</td>
			</tr>
		</table>
		
		<s:url id="GoogleEarthIconUrl" value="/icons/GoogleEarth.jpg" />
		<s:url id="YouTubeIconUrl" value="/icons/YouTube.jpg" />
		
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="webApplicationPlugins" />
		</p>
		
		<table class="aboutWebAppIconsTable" >
			<tr>
				<td>
					<img src='<s:property value="%{#GoogleEarthIconUrl}" />' title='Google Earth' width="64" height="64" class="ui-corner-all" />				
				</td>
				<td>
					<img src='<s:property value="%{#YouTubeIconUrl}" />' title='YouTube' width="64" height="64" class="ui-corner-all" />				
				</td> 
			</tr>
			<tr>
				<td>
					<a href='<s:text name="googleEarthPluginUrl" />' target="_blank"><s:text name="googleEarthPlugin" /></a>	
				</td>
				<td>
					<a href='<s:text name="adobeFlashPluginUrl" />' target="_blank"><s:text name="adobeFlashPlugin" /></a>	
				</td>
			</tr>
		</table>
		
		<p class="paragraph">	
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="webApplicationMenu" />
		</p>
		
		<table id="aboutWebAppUniversalMenuTable" >
			<tr>
				<td>
					<div class="universalMenuButton ui-corner-all"> 
						<s:text name="professionals" /> &gt; <s:a href="%{whatDoWeDoPresentationActionUrl}"><s:text name="whatDoWeDo" /></s:a> | <s:a href="%{whoAreWePresentationActionUrl}"><s:text name="whoAreWe" /></s:a> | <s:a href="%{contactUsPresentationActionUrl}"><s:text name="contactUs" /></s:a>
					</div>				
				</td> 
			</tr>

		</table>
	
		<table id="aboutWebAppMenuTable">
			<tr>	
				<td>
					<a id="aboutTechnologiesMenu" tabindex="0" href="#aboutTechnologiesMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
						<span class="ui-icon ui-icon-triangle-1-s"></span>
						<s:text name="technologies" />
					</a>
					<div id="aboutTechnologiesMenuItems" class="hidden menu">
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
			</tr>
		</table>
		
		<p class="paragraph">	
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="languageSupport" />
		</p>

		<script type="text/javascript">
			$(
				function() {
			    	$('.fg-button').hover(
						function(){ $(this).removeClass('ui-state-default').addClass('ui-state-focus'); },
					    function(){ $(this).removeClass('ui-state-focus').addClass('ui-state-default'); }
					 );
					
					$('#aboutTechnologiesMenu').menu({ 
						content: $('#aboutTechnologiesMenu').next().html(),
						showSpeed: 400,
						flyOut: true
					});
				}
			);
		</script>
	</div>