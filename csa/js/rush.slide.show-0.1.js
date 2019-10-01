	(function($) {
	    $.fn.rushSlideShow = function(options) {
			var settings = $.extend({
			    photoPane: '#photoPane',
				photoElement: '#photoDisplay',
				photoDescription: '#photoDescription',
				currentPhoto: '#currentPhoto',
				title: 'Photos',
				next: 'Next',
				previous: 'Previous',
				first: 'First',
				last: 'Last',
				play: 'Play',
				stop: 'Stop',
				close: 'Close',
				document: document,
				playButton:'#playButton',
				previousButton:'#previousButton',
				firstButton:'#firstButton',
				nextButton:'#nextButton',
				lastButton:'#lastButton',
				helpButton:'#helpButton',
				closeButton:'#closeButton',
				zIndex: 1,
				hasDialog: true,
				stack:false,
				dialogWidth:850,
				dialogPosition: "center",
				imageWidth: 800,
				imageHeight: 600,
				delay: 3000,
				image: "Image",
				of: "of",
				help: "Help",
				helpTitle: "HOW TO WATCH PICTURES",
				helpText: "You can press the buttons with the mouse; or use the following keys: P (Play/Pause), UP (First), LEFT (Previous), RIGHT (Next), DOWN (Last)",
				language: "en"
			}, options||{});
			
				if(settings.language == "es") {
					settings.next = 'Siguiente';
					settings.previous = 'Previa';
					settings.first = 'Primera';
					settings.last = 'Última';
					settings.play = 'Reproducir';
					settings.pause = 'Pausa';
					settings.stop = 'Detener';
					settings.close = 'Cerrar';
					settings.image = "Imagen";
					settings.of = "de";
					settings.help = "Ayuda";
					settings.helpTitle = "PARA VER FOTOS";
					settings.helpText = "Puedes usar los botones del mouse o las teclas: P (Presentación / Pausa), ARRIBA (Primera), IZQUIERDA (Previa), DERECHA (Próxima), ABAJO (Última)";
				} else if(settings.language == "pt") {
					settings.next = 'Seguinte';
					settings.previous = 'Anterior';
					settings.first = 'Primeira';
					settings.last = 'Última';
					settings.play = 'Reproduzir';
					settings.pause = 'Pausa';
					settings.stop = 'Parar';
					settings.close = 'Fechar';
					settings.image = "Imagem";
					settings.of = "de";
					settings.help = "Ajuda";
					settings.helpTitle = "PARA VER FOTOS";
					settings.helpText = "Poderá usar os botões com o mouse ou as teclas: P (Apresentação / Parar), ACIMA (Primeira), ESQUERDA (anterior), DIREITA (Seguinte), ABAIXO (Última)";
				}

			
			if(settings.hasDialog == true) { 
				$(settings.photoPane).dialog({
					autoOpen:false, 
					width:settings.dialogWidth,
					title:settings.dialogTitle,
					position:settings.dialogPosition,
					zIndex:settings.zIndex,
					stack:settings.stack,
					create: function(event, ui) {
						
						$(this).parent().find('.ui-dialog-titlebar-close').bind('click', 
							function(event) {
								$(this).dialog('close');
							}
						);		      
		
						$(this).parent().find('.ui-dialog-buttonpane button:first-child').attr("id", settings.playButton.substring(1)).button({
		        	        icons: { secondary: 'ui-icon-play' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(2)').attr("id", settings.firstButton.substring(1)).button({
		        	        icons: { primary: 'ui-icon-seek-first' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(3)').attr("id", settings.previousButton.substring(1)).button({
		        	        icons: { primary: 'ui-icon-seek-prev' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(4)').attr("id", settings.nextButton.substring(1)).button({
		        	        icons: { secondary: 'ui-icon-seek-next' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(5)').attr("id", settings.lastButton.substring(1)).button({
		        	        icons: { secondary: 'ui-icon-seek-end' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(6)').attr("id", settings.helpButton.substring(1)).button({
		        	        icons: { secondary: 'ui-icon-help' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(7)').attr("id", settings.closeButton.substring(1)).button({
		        	        icons: { secondary: 'ui-icon-closethick'}
		        	    });

					},
					close: function(event, ui) {
						var playButton = $(settings.photoPane).parent().find('.ui-dialog-buttonpane button:first-child');
	       	         	
						if(playButton.hasClass('isPlaying')) {
							window.clearInterval(settings.tick);
							playButton.removeClass('isPlaying');
							playButton.button("option", "label", settings.play);
							playButton.button("option", "icons", {secondary:"ui-icon-play"});
						}
						
						var helpButton = $(this).parent().find('.ui-dialog-buttonpane button:nth-child(6)');
						
	        			 if(helpButton.hasClass('isOpen')) {
	        				 helpButton.removeClass('isOpen');
	        				 $('#suggester').fadeOut('slow').remove();
	        			 }
					},
		        	buttons: [{
		        		text: settings.play,
		                click: function(event) {
		                
		                	if($(event.target).hasClass('isPlaying')) {
		                		window.clearInterval(settings.tick);
		         		        $(event.target).removeClass('isPlaying');
		         		        $(event.target).button("option", "label", settings.play);
		         		        $(event.target).button("option", "icons", {secondary:"ui-icon-play"});
		                	} else {
		                		settings.tick = window.setInterval(
		       	        		     	 	
		                		function() {
		                			showPhoto((settings.current + 1) % settings.thumbnails$.length); 
		                		}, settings.delay);
		               		              
		               		    $(event.target).addClass('isPlaying');
		               		    showPhoto((settings.current + 1) % settings.thumbnails$.length);
		               		    $(event.target).button("option", "label", settings.stop);
		               		    $(event.target).button("option", "icons", {secondary:"ui-icon-pause"});
		                	}
		                }
		             },
		             {
		            	 text: settings.first,
		                 click: function() {
		            	     showPhoto(0);	 
		            	 }
		             	
		             },
		        	 {
		        		 text: settings.previous,
		        		 click: function() {
		        			 showPhoto((settings.thumbnails$.length + settings.current - 1) % settings.thumbnails$.length); 
		        		 }
		        	 },
		        	 {
		        		 text: settings.next,
		        		 click: function() {
		        			 showPhoto((settings.current + 1) % settings.thumbnails$.length); 
		        	     }
		        	 },
		        	 {
		        		 text: settings.last,
		        		 click: function() {
		        			 showPhoto(settings.thumbnails$.length - 1);
		        		 }
		        	 },
		        	 {
		        		 text: settings.help,
		        		 click: function(event) {
		        			 
		        			 if($(settings.helpButton).hasClass("isOpen")) {
		        				 $(settings.helpButton).removeClass('isOpen');
		        				 $('#suggester').fadeOut(200).remove();
		        			 } else {
		        				 $(settings.helpButton).addClass('isOpen');
		        				 
		        				 $('<div>')
									.attr('id', 'suggester')
									.addClass('ui-widget')
									.addClass('ui-state-highlight') 
									.addClass('ui-corner-all')
									.css({
									  		position: 'absolute',
									  		top: event.pageY - 35,
									  		left: event.pageX + 35,
									  		fontSize: 'x-small',
									  		textAlign: 'center',
									  		verticalAling: 'middle',
									  		fontFamily: 'segoe ui, Arial, sans-serif',
									  		fontWeight: 'bold',
									  		color:'green',
									  		width: '256px',
									  		height: '104px',
									  		zIndex: 5
									  	})
									  	.html(settings.helpTitle + '<br />' + settings.helpText)
									  	.appendTo('body')
									  	.fadeIn(200)
									  	.click(
									  		function() {
									  			$('#helpButton').triggerHandler("click");
									  		}
									  	);
		        			 }
		        			 
		        			 
		        		 }
		        	 },
		        	 {
		        		 text: settings.close,
		        		 click: function(event) {        			 
		        			 $(this).dialog("close");
		        		 }
		        	 }]
				 });
				
			} else {

				$(settings.playButton).button(
					{
		        		label: settings.play,
		                icons: {secondary: 'ui-icon-play'}
					}
				).click(
					function(event) {        
		               	if($(settings.playButton).hasClass('isPlaying')) {
		               		window.clearInterval(settings.tick);
		               		$(settings.playButton).removeClass('isPlaying');
		               		$(settings.playButton).button("option", "label", settings.play);
		               		$(settings.playButton).button("option", "icons", {secondary:"ui-icon-play"});
		                } else {
		                	settings.tick = window.setInterval(
		       	        		     	 	
		                	function() {
		                		showPhoto((settings.current + 1) % settings.thumbnails$.length); 
		                	}, settings.delay);
		               		              
		                	$(settings.playButton).addClass('isPlaying');
		               		showPhoto((settings.current + 1) % settings.thumbnails$.length);
		               		$(settings.playButton).button("option", "label", settings.stop);
		               		$(settings.playButton).button("option", "icons", {secondary:"ui-icon-pause"});
		                }
		            }
				);
				
				$(settings.firstButton).button(
					{
						label: settings.first,
						icons: { primary: 'ui-icon-seek-first' }
					}
				).click(
					function() {
						showPhoto(0);	 
					}
				);
				
				$(settings.previousButton).button(
					{
						label: settings.previous,
			        	icons: { primary: 'ui-icon-seek-prev' }
			        }
				).click(
					function() {
						showPhoto((settings.thumbnails$.length + settings.current - 1) % settings.thumbnails$.length);
					}
				);
				
				$(settings.nextButton).button(
					{
						label: settings.next,
			        	icons: { secondary: 'ui-icon-seek-next' }
					}
				).click(
					function() {
						showPhoto((settings.current + 1) % settings.thumbnails$.length); 
					}
				);
				
				$(settings.lastButton).button(
					{
						label: settings.last,
			        	icons: { secondary: 'ui-icon-seek-end' }
					}
				).click(
					function() {
						showPhoto(settings.thumbnails$.length - 1);
			        }
				);
				
				$(settings.helpButton).button(
						{
							label: settings.help,
					       	icons: { secondary: 'ui-icon-help' }
						}
				).toggle(
					function(event) {
						$('<div>')
							.attr('id', 'suggester')
						  	.addClass('ui-widget')
						  	.addClass('ui-state-highlight') 
						  	.addClass('ui-corner-all')
						  	.css({
						  		position: 'absolute',
						  		top: event.pageY - 35,
						  		left: event.pageX + 35,
						  		fontSize: 'x-small',
						  		textAlign: 'center',
						  		verticalAling: 'middle',
						  		fontFamily: 'segoe ui, Arial, sans-serif',
						  		fontWeight: 'bold',
						  		color:'green',
						  		width: '256px',
						  		height: '104px'								
						  	})
						  	.html(settings.helpTitle + '<br />' + settings.helpText)
						  	.appendTo('body')
						  	.fadeIn('slow')
						  	.click(
						  		function() {
						  			$('#helpButton').triggerHandler("click");
						  		}
						  	);
					},
					function() {
					   	$('#suggester')
					   		.fadeOut(200)
					   		.remove();
						}				    
					);
			}

		    function showPhoto(index) {
			    $(settings.photoElement).removeAttr("src").attr('src', settings.thumbnails$[index].src);
			    settings.current = index;      
			    $(settings.photoDescription).html(settings.thumbnails$[index].title);
			    $(settings.currentPhoto).html(settings.image + ' ' + (settings.current + 1) + ' ' + settings.of + ' ' + settings.thumbnails$.length);
		    }
		
		    $(settings.photoElement)
		    	.attr('width', settings.imageWidth)
		    	.attr('height', settings.imageHeight)
		    	.css('cursor','pointer')
		    	.click(function() {
		    		showPhoto((settings.current + 1) % settings.thumbnails$.length);
		    	}
		    );
		    
		    settings.current = 0;
		    settings.thumbnails$ = this.filter('img');
		    settings.thumbnails$.each(
		        function(n) { 
		        	$(this).data('photo-index', n); 
		        }
		    ).click(
		    	function() {
		    		showPhoto($(this).data('photo-index'));
		    		
		    		if(settings.hasDialog == true) {
		    			$(settings.photoPane).dialog('open');
		    		}
		    	}
		    );
		    
		    $(settings.document).bind("keydown",
		    		
		    	function(event) {
					var keyCode = (event.keyCode ? event.keyCode : event.which);
					var Arrow = {LEFT: 37, UP: 38, RIGHT: 39, DOWN: 40 };
					var Control = {PLAY: 80};
				
					switch (keyCode) {
						case Control.PLAY:
							$(settings.playButton).triggerHandler("click");
				    	break;	  
						case Arrow.LEFT:
							$(settings.previousButton).triggerHandler("click");
					   	break;
						case Arrow.UP:
							$(settings.firstButton).triggerHandler("click");
						break;
						case Arrow.RIGHT:
							$(settings.nextButton).triggerHandler("click");
					   	break;
						case Arrow.DOWN:
							$(settings.lastButton).triggerHandler("click");
						break;
					}
				}
			);
		    
		    showPhoto(0);
		    
		    return this;
		};
	})(jQuery);