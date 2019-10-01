	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	 
	<h1 class="title">
		<s:text name="whoAreWeTitle" />
	</h1>
	<div>
		<p class="paragraph">
			&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="whoAreWeDescription" />  
		</p>	
	</div>

	<s:url id="AmayaPhotoUrl" action="WhoWeArePresentationPhotoDownloadFileAction_getFile" namespace="/Util" escapeAmp="false">
		<s:param name="fileName">Amaya.jpg</s:param>
		<s:param name="contentType">image/jpeg</s:param>					 
	</s:url>
	
	<s:url id="CrustaPhotoUrl" action="WhoWeArePresentationPhotoDownloadFileAction_getFile" namespace="/Util" escapeAmp="false">
		<s:param name="fileName">Crusta.jpg</s:param>
		<s:param name="contentType">image/jpeg</s:param>					 
	</s:url>

	<div id="whoWeAre">
		<s:if test="seed == 1">
			<sj:tabbedpanel id="amayaTabbedPanel">
				<sj:tab id="amayaDescriptionTab" label="%{getText('amayaTitle')}" target="whoWeAreProfessionalAmaya" />
				
				<div id="whoWeAreProfessionalAmaya">
					<div id="whoWeAreProfessionalPhoto">
						<img src='<s:property value="%{#AmayaPhotoUrl}" />' width="160" height="106" class="ui-corner-all isPhoto" title="Ingeniero Agrónomo Humberto Eduardo Amaya" alt="Ingeniero Agrónomo Humberto Eduardo Amaya" />			
					</div>
					<div id="whoWeAreProfessionalDescription">
						<p class="paragraph"> 
							&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="amayaDescription" />
						</p>
						<p class="paragraph">
							<b><s:text name="phoneNumberTitle" />:</b> <s:text name="amayaPhoneNumber" /> <br />
							<b><s:text name="emailTitle" />:</b> <s:text name="amayaEmail" />
						</p>
					</div>	
				</div>
			</sj:tabbedpanel>

			<sj:tabbedpanel id="crustaTabbedPanel">
			
				<sj:tab id="crustaDescriptionTab" label="%{getText('crustaTitle')}" target="whoWeAreProfessionalCrusta" />
				
				<div id="whoWeAreProfessionalCrusta">
					<div id="whoWeAreProfessionalPhoto">
						<img src='<s:property value="%{#CrustaPhotoUrl}" />' width="160" height="106" class="ui-corner-all isPhoto" title="Ingeniero Agrónomo Luis Crusta" alt="Ingeniero Agrónomo Luis Crusta" />
					</div>
					<div id="whoWeAreProfessionalDescription">
						<p class="paragraph"> 
							&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="crustaDescription"></s:text>
						</p>
						<p class="paragraph">
							<b><s:text name="phoneNumberTitle" />:</b> <s:text name="crustaPhoneNumber" /> <br />
							<b><s:text name="emailTitle" />:</b> <s:text name="crustaEmail" />
						</p>
					</div>			
				</div>

			</sj:tabbedpanel>
		</s:if>
		<s:else>
			<sj:tabbedpanel id="crustaTabbedPanel">
				<sj:tab id="crustaDescriptionTab" label="%{getText('crustaTitle')}" target="whoWeAreProfessionalCrusta" />
				
				<div id="whoWeAreProfessionalCrusta">
					<div id="whoWeAreProfessionalPhoto">
						<img src='<s:property value="%{#CrustaPhotoUrl}" />' width="160" height="106" class="ui-corner-all isPhoto" title="Ingeniero Agrónomo Luis Crusta" alt="Ingeniero Agrónomo Luis Crusta" />
					</div>
					<div id="whoWeAreProfessionalDescription">
						<p class="paragraph"> 
							&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="crustaDescription"></s:text>
						</p>
						<p class="paragraph">
							<b><s:text name="phoneNumberTitle" />:</b> <s:text name="crustaPhoneNumber" /> <br />
							<b><s:text name="emailTitle" />:</b> <s:text name="crustaEmail" />
						</p>
					</div>			
				</div>
			</sj:tabbedpanel>
			<sj:tabbedpanel id="amayaTabbedPanel">
				<sj:tab id="amayaDescriptionTab" label="%{getText('amayaTitle')}" target="whoWeAreProfessionalAmaya" />
				
				<div id="whoWeAreProfessionalAmaya">
					<div id="whoWeAreProfessionalPhoto">
						<img src='<s:property value="%{#AmayaPhotoUrl}" />' width="160" height="106" class="ui-corner-all isPhoto" title="Ingeniero Agrónomo Humberto Eduardo Amaya" alt="Ingeniero Agrónomo Humberto Eduardo Amaya" />			
					</div>
					<div id="whoWeAreProfessionalDescription">
						<p class="paragraph"> 
							&nbsp; &nbsp; &nbsp; &nbsp;<s:text name="amayaDescription" />
						</p>
						<p class="paragraph">
							<b><s:text name="phoneNumberTitle" />:</b> <s:text name="amayaPhoneNumber" /> <br />
							<b><s:text name="emailTitle" />:</b> <s:text name="amayaEmail" />
						</p>
					</div>	
				</div>
			</sj:tabbedpanel>
		</s:else>
	</div>
	
	<div id="photoPane">
    	<img id="photoDisplay" src=""/>
    	<div id="photoDescription"></div>
    	<div id="currentPhoto"></div>
    </div>
	
	<script type="text/javascript">
		$("#photoPane").hide();
	
		$(
			function() {
				var language = '<s:property value="%{#language}"/>';
				
				if(language == "es") {
					photosDialogTitle="¿Quiénes Somos?";
	  			} else if(language == "pt") {
	  				photosDialogTitle="Quem Somos?";
	  			} else {
	  				photosDialogTitle = "Who are we?";
	  			}	
				
		  		$(".isPhoto").rushSlideShow({
		       		hasDialog:true,
			        dialogTitle:photosDialogTitle,
					language:language
				});
			}
		);
	</script>
	