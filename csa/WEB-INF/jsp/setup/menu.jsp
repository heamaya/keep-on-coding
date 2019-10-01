	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %> 
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
	<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<%@ taglib prefix="security" uri='http://www.springframework.org/security/tags' %>
	
	<s:url id="homePresentationPhotoActionUrl" action="HomePresentationPhotoAction" namespace="/setup" />
	<s:url id="newsActionUrl" action="NewsAction" namespace="/setup" />
	<s:url id="terracePresentationPhotoActionUrl" action="TerracePresentationPhotoAction" namespace="/setup" />
	<s:url id="dikePresentationPhotoActionUrl" action="DikePresentationPhotoAction" namespace="/setup" />
	<s:url id="channelPresentationPhotoActionUrl" action="ChannelPresentationPhotoAction" namespace="/setup" />
	<s:url id="gullyRecoveryPresentationPhotoActionUrl" action="GullyRecoveryPresentationPhotoAction" namespace="/setup" />
	<s:url id="erosionPresentationPhotoActionUrl" action="ErosionPresentationPhotoAction" namespace="/setup" />
	<s:url id="setupAccountConfigurationActionUrl" action="AdministratorAccountConfigurationAction" namespace="/setup" />
	<s:url id="visitorMessageActionUrl" action="VisitorMessageAction" namespace="/setup" />
	<s:url id="consortiumActionUrl" action="ConsortiumAction" namespace="/setup" />
	<s:url id="systematizationActionUrl" action="SystematizationAction" namespace="/setup" />
	<s:url id="farmerActionUrl" action="FarmerAction" namespace="/setup" />
	<s:url id="landActionUrl" action="LandAction" namespace="/setup" />
	<s:url id="consortiumActionUrl" action="ConsortiumAction" namespace="/setup" />
	<s:url id="legalPersonActionUrl" action="LegalPersonAction" namespace="/setup" />
	<s:url id="anonymousCommercialSocietyActionUrl" action="AnonymousCommercialSocietyAction" namespace="/setup" />
	<s:url id="limitedResponsibilityCommercialSocietyActionUrl" action="LimitedResponsibilityCommercialSocietyAction" namespace="/setup" />
	<s:url id="factCommercialSocietyActionUrl" action="FactCommercialSocietyAction" namespace="/setup" />
	<s:url id="accountConfigurationActionUrl" action="AccountConfigurationAction" namespace="/setup" />
	<s:url id="videoActionUrl" action="VideoAction" namespace="/setup" />
	<s:url id="journalIncomeEntryActionUrl" action="JournalIncomeEntryAction" namespace="/setup" />
	<s:url id="journalOutcomeEntryActionUrl" action="JournalOutcomeEntryAction" namespace="/setup" />
	<s:url id="journalActionUrl" action="JournalAction" namespace="/setup" />
	<s:url id="journalSummaryActionUrl" action="JournalSummaryAction" namespace="/setup" />

	<table id="menuTable">
		<tr>
			<td>
				<a id="presentationMenu" tabindex="0" href="#presentationMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					Presentación
				</a>
				<div id="presentationMenuItems" class="hidden menu">
					<ul>
						<li>
							<s:a id="startMenu" href="#">Inicio</s:a>
							<ul>
								<li><s:a href="%{homePresentationPhotoActionUrl}">Presentación</s:a></li>
								<li>
									<s:a id="professionalsMenu" href="#">Profesionales</s:a>
									<ul>
										<li><s:a href="%{erosionPresentationPhotoActionUrl}">¿Qué Hacemos? - Fotos Erosión</s:a></li>
										<li><s:a href="%{visitorMessageActionUrl}">Contáctenos - Mensajes</s:a></li>
									</ul>
								</li>
								<li><s:a href="%{newsActionUrl}">Noticias</s:a></li>
								<li><s:a href="%{videoActionUrl}">Videos</s:a></li>
							</ul>
						</li>
						<li>
							<s:a id="technologiesMenu" href="#">Tecnologías</s:a>
							<ul>
								<li><s:a href="%{terracePresentationPhotoActionUrl}">Terrazas</s:a></li>
								<li><s:a href="%{dikePresentationPhotoActionUrl}">Diques</s:a></li>
								<li><s:a href="%{channelPresentationPhotoActionUrl}">Canales</s:a></li>
								<li><s:a href="%{gullyRecoveryPresentationPhotoActionUrl}">Recuperación de Cárcavas</s:a></li>
							</ul>
						</li>

						<!-- 
						<li>
							<s:a id="farmersMenu">Productores</s:a>
							<ul>
								<li><s:a href="%{consortiumPresentationActionUrl}">Consorcios</s:a></li>
								<li><s:a href="%{systematizationActionUrl}">Trabajos Destacados</s:a></li>
							</ul>
						</li>
						 -->
					</ul>
				</div>
			</td>
			<td>
				<a id="administrationMenu" tabindex="0" href="#administratorMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					Administración
				</a>
				<div id="administrationMenuItems" class="hidden menu">
					<ul>
						<li><s:a href="%{farmerActionUrl}">Productores Agropecuarios</s:a></li>
						<li><s:a href="%{landActionUrl}">Campos</s:a></li>
						<li><s:a href="%{consortiumActionUrl}">Consorcios</s:a></li>
						<li><s:a href="%{systematizationActionUrl}">Sistematizaciones</s:a></li>
						<li>
							<s:a id="companiesMenu" href="#">Empresas Agropecuarias</s:a>
							<ul>
								<li><s:a href="%{legalPersonActionUrl}">Personas Legales</s:a></li>
								<li>
									<s:a id="commercialSocietiesMenu" href="#">Sociedades Comerciales</s:a>
									<ul>
										<li><s:a href="%{anonymousCommercialSocietyActionUrl}">Sociedades Anónimas</s:a></li>
										<li><s:a href="%{factCommercialSocietyActionUrl}">Sociedades de Hecho</s:a></li>
										<li><s:a href="%{limitedResponsibilityCommercialSocietyActionUrl}">Sociedades de Responsabilidad Limitada</s:a></li>
									</ul>
								</li>
							</ul>
						</li>
						<security:authorize ifAllGranted="FINANCE">
							<s:url id="systematizationProjectActionUrl" action="SystematizationProjectAction" namespace="/setup" />
							<li><s:a href="%{systematizationProjectActionUrl}">Proyectos de Sistematización</s:a></li>
						</security:authorize>
						<security:authorize ifAllGranted="FINANCE">
							<s:url id="dikeActionUrl" action="DikeAction" namespace="/setup" />
							<li><s:a href="%{dikeActionUrl}">Diques</s:a></li>
						</security:authorize>
						<security:authorize ifAllGranted="FINANCE">
							<s:url id="headerGullyWorkActionUrl" action="HeaderGullyWorkAction" namespace="/setup" />
							<li><s:a href="%{headerGullyWorkActionUrl}">Obras de Cabecera de Cárcava</s:a></li>
						</security:authorize>
						<security:authorize ifAllGranted="FINANCE">
							<s:url id="paymentActionUrl" action="PaymentAction" namespace="/setup" />
							<li><s:a href="%{paymentActionUrl}">Pagos</s:a></li>
						</security:authorize>
						<security:authorize ifAllGranted="USERS">
							<s:url id="userActionUrl" action="UserAction" namespace="/setup/user" />
							<li><s:a href="%{userActionUrl}">Usuarios</s:a></li>
						</security:authorize>
						<li>
							<s:a id="accountantMenu" href="#">Contabilidad</s:a>
							<ul>
								<li><s:a href="%{journalIncomeEntryActionUrl}">Ingresos</s:a></li>
								<li><s:a href="%{journalOutcomeEntryActionUrl}">Egresos</s:a></li>
								<li><s:a href="%{journalActionUrl}">Cierre Mensual</s:a></li>
								<li><s:a href="%{journalSummaryActionUrl}">Libro Diario</s:a></li>
								<security:authorize ifAllGranted="USERS">
									<s:url id="journalEntryUtilityActionUrl" action="JournalEntryUtilityAction" namespace="/setup" />
									<li><s:a href="%{journalEntryUtilityActionUrl}">Utilidad Ingresos - Egresos</s:a></li>
								</security:authorize>
								<security:authorize ifAllGranted="USERS">
									<s:url id="journalUtilityActionUrl" action="JournalUtilityAction" namespace="/setup" />
									<li><s:a href="%{journalUtilityActionUrl}">Utilidad Cierres de Mes</s:a></li>
								</security:authorize>
							</ul>
						</li>
					</ul>
				</div>
			</td>

			<td>
				<a id="accountMenu" tabindex="0" href="#accountMenuItems" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" >
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					Cuenta
				</a>
				<div id="accountMenuItems" class="hidden menu">
					<ul>
						 
						<li><s:a href="%{accountConfigurationActionUrl}">Configuración</s:a></li>
						<li><a href="<%=request.getContextPath()%>/logout/j_spring_security_logout">Salir</a></li>
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
					$('#presentationMenu').menu({ 
						content: $('#presentationMenu').next().html(),
						showSpeed: 200,
						crossSpeed: 200, 
						backLink:true
					});		    				    		
		    	} else {
					$('#presentationMenu').menu({ 
						content: $('#presentationMenu').next().html(),
						showSpeed: 400,
						flyOut: true
					});
		    	}

		    	if($.browser.msie == true) {
					$('#administrationMenu').menu({ 
						content: $('#administrationMenu').next().html(),
						showSpeed: 200,
						crossSpeed: 200, 
						backLink:true
					});		    				    		
		    	} else {
					$('#administrationMenu').menu({ 
						content: $('#administrationMenu').next().html(),
						showSpeed: 400,
						flyOut: true
					});
		    	}
				
				$('#accountMenu').menu({ 
					content: $('#accountMenu').next().html(),
					showSpeed: 400,
					flyOut: true
				});
			}
		);
	</script>