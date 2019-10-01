	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s"%>
	
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>

	<div id="loginFormDiv">
	
		<form id="LoginAction" action="./j_spring_security_check" method="post" enctype="application/x-www-form-urlencoded;charset=utf-8">
			<input type='hidden' name='_spring_security_remember_me' value="true" />
		
			<table class="wwFormTable">
				<tr>
	    			<td class="loginFormTitle" colspan="2">
	    				<s:text name="accessConditions" />
	    			</td>
				</tr>
				<tr>
	    			<td class="tdLabel"><label for="j_username_input" class="label"><s:text name="username"/><span class="required">*</span>:</label></td>
	    			<td><input type="text" name="j_username" size="30" maxlength="256" value="" id="j_username_input" class="message"/></td>
				</tr>
				<tr>
	    			<td class="tdLabel"><label for="j_password_input" class="label"><s:text name="password"/><span class="required">*</span>:</label></td>
	    			<td><input type="password" name="j_password" size="30" maxlength="256" value="" id="j_password_input" class="message"/></td>
				</tr>
				
				<tr>
					<td colspan="2" class="recaptchaContentPane">
						<div id="recaptchaTitle">
							<s:text name="human"></s:text>:
						</div>		
						<div id="recaptchaDiv">
						</div>
							
						<input type="hidden" name="recaptcha_response_field" value="manual_challenge">
						<br />
					</td>

				</tr>
	 			<tr>
					<td colspan="2">
						<div>
			    			<s:if test="#parameters.size() > 0">
			    				<s:div cssClass="loginError">
			    					<s:if test="#session['SPRING_SECURITY_LAST_EXCEPTION'] instanceof org.springframework.security.AuthenticationServiceException">
			    						<s:text name="incorrectUsernameOrPassword"></s:text>
			    					</s:if>
			    					<s:elseif test="#session['SPRING_SECURITY_LAST_EXCEPTION'] instanceof org.springframework.security.BadCredentialsException">
				    					<s:text name="incorrectUsernameOrPassword"></s:text>
			    					</s:elseif>
			   						<s:elseif test="#session['SPRING_SECURITY_LAST_EXCEPTION'] != null ">
										<s:property value="#session['SPRING_SECURITY_LAST_EXCEPTION'].message" />
									</s:elseif>
									<s:elseif test="exception != null">
										<s:property value="exception.message" />
									</s:elseif>
									<s:actionerror/>
								</s:div>
								</s:if>
							<s:else>
								<s:actionerror/>
								&nbsp;
							</s:else>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript">
		$(
			function() {
				var language = '<s:property value="%{#language}"/>';
				
				if(language == "es") {
					login = "Iniciar Sesión";
	  			} else if(language == "pt") {
	  				login = "Login";
	  			} else {
	  				login = "Login";
	  			}
				
				$("#LoginAction").validate({
					rules: {
						j_username: {
							required: true,
							email: true
						},
						j_password: {
							required: true
						}
					}, invalidHandler: function(form, validator) {
						$("#loginFormDiv").parent().find('.ui-dialog-buttonpane button:first-child').button("enable");
					}
				});
				
				$("#loginFormDiv").dialog({
					autoOpen:true, 
		  		    width:550,
		  		    position:"center",
		  		    closeOnEscape:false,
		  			title:login,
		  			resizable:false,
		  			draggable:true,
		  			modal:false,
		  			stack:false,
		  			zIndex:1,
					create: function(event, ui) {
						Recaptcha.create("6LfItsMSAAAAAA0bId08yCnXOHzB4sFOPZ0Qzgpl", "recaptchaDiv", {theme: "clean", callback: Recaptcha.focus_response_field});
				        $('.ui-dialog-titlebar-close').remove();
					},
					buttons: [
						{
							text:login,
							click: function(event) {
								$("#loginFormDiv").parent().find('.ui-dialog-buttonpane button:first-child').button("disable");
								$("#LoginAction").submit();
							}
						}
					]
				});
			}
		);
	</script>