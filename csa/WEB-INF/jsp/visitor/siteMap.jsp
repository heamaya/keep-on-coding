	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	 
	<%@ taglib uri="/struts-tags" prefix="s"%>

	<h1 class="title">
		<s:text name="siteMap" />
	</h1>

	<table class="siteMap">
		<thead>
			<tr>
				<th>	
					<s:text name="start" />
				</th>
				<th>	
					<s:text name="technologies" />
				</th>
				<th>	
					<s:text name="conservationistFarmers" />
				</th>
				<th>
					<s:text name="help" />
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
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
					</ul>
					<s:text name="professionals" />
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
					<s:text name="language" />
					<ul>
						<li>
							<s:a href="%{changeLocaleToSpanishActionUrl}"><s:text name="spanish" /></s:a>
						</li>
						<li>
							<s:a href="%{changeLocaleToPortugueseActionUrl}"><s:text name="portuguese" /></s:a>
						</li>
						<li>
							<s:a href="%{changeLocaleToEnglishActionUrl}"><s:text name="english" /></s:a>
						</li>
					</ul>
				</td>
				<td>
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
							<s:a href="%{gullyRecoveryPresentationActionUrl}"><s:text name="gulliesRecovery" /></s:a>
						</li>
					</ul>
				</td>
				<td>
					<ul>
						<li>
							<s:a href="%{consortiumPresentationActionUrl}"><s:text name="consortia" /></s:a>
						</li>
						<li>
							<s:a href="%{systematizationPresentationActionUrl}"><s:text name="starredWorks" /></s:a>
						</li>
					</ul>
				</td>
				<td>
					<ul>
						<li>
							<s:a href="%{aboutWebApplicationPresentationActionUrl}"><s:text name="aboutWebApplication" /></s:a>
						</li>
					</ul>
				</td>
					
			</tr>
		</tbody>
	</table>