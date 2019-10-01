	(function($) {
	    $.fn.rushYouTubeVideoShow = function(options) {
			var settings = $.extend({
			    videoPane: '#videoPane',
				videoDiv: "#videoDiv",
				videoBar: "#videoBar",
				ytPlayer: "#myYtPlayer",
				videoDescription: '#videoDescription',
				currentVideo: '#currentVideo',
				title: 'Videos',
				next: 'Next',
				previous: 'Previous',
				first: 'First',
				last: 'Last',
				play: 'Play',
				pause: 'Pause',
				stop: 'Stop',
				close: 'Close',
				error: 'An error occured of type:',
				document: document,
				ytPlayer: ytPlayer,
				zIndex: 1,
				hasDialog: true,
				stack:false,
				dialogWidth:850,
				dialogPosition: "center",
				imageWidth: 800,
				imageHeight: 600,
				delay: 250,
				video: "Video",
				of: "of",
				validVolume: "Please enter a valid volume between 0 and 100.",
				mute: "Mute",
				unMute: "Unmute",
				help: "Help",
				helpTitle: "HOW TO WATCH VIDEOS",
				helpText: "You can press the buttons with the mouse; or the keys: P(Play/Pause), S(Sound/Mute), UP(First), LEFT(Previous), RIGHT(Next), DOWN(Last)",
				language:"en"
			}, options||{});
				
			if(settings.language == "es") {
				settings.play = "Reproducir";
				settings.pause = "Pausa";
				settings.first = "Primero";
				settings.last = "Último";
				settings.next = "Siguiente";
				settings.previous = "Anterior";
				settings.close = "Cerrar";
				settings.image = "Foto";
				settings.of = "de";
				settings.help = "Ayuda";
				settings.error = 'Ocurrió un error de tipo =';				
				settings.video = "Video" ;
				settings.of = "de" ;
				settings.validVolume = "Por favor ingresá un número entre 0 y 100." ;
				settings.mute = "Mudo" ;
				settings.unMute = "Sonido" ;
				settings.help = "Ayuda" ;
				settings.helpTitle ="CÓMO VER VIDEOS";
				settings.helpText = "Puedes usar los botones con el mouse; o las teclas: P (Play/Pausa), S (Sonido/Mudo), ARRIBA (Primero), IZQUIERDA (Previo), DERECHA (Siguiente), ABAJO (Último). Además, podés adelantar o retroceder el video con el mouse haciendo clic en la barra que se desplaza.";
			} else if(settings.language == "pt") {
				settings.play = "Reproduzir";
				settings.pause = "Pausa";
				settings.first = "Primeiro";
				settings.last = "Último";
				settings.next = "Seguinte";
				settings.previous = "Anterior";
				settings.close = "Fechar";
				settings.of = "de";
				settings.error = 'Aconteceu um Erro =';				
				settings.video = "Vídeo" ;
				settings.of = "de" ;
				settings.validVolume = "Por favor, insira um número entre 0 e 100." ;
				settings.mute = "Mudo" ;
				settings.unMute = "Som" ;
				settings.help = "Ajuda" ;
				settings.helpTitle ="CÓMO VER VIDEOS";
				settings.helpText = "Poderá usar os botões com o mouse ou as teclas: P (Reprodução / Pausa), S (Som/Mudo), ACIMA (Primeira), ESQUERDA (anterior), DIREITA (Seguinte), ABAIXO (Última). Além disso, pode avançar ou retroceder o vídeo com o mouse, clicando sobre a barra de deslocamento.";
			}
			
		    $(settings.ytPlayer).attr('width', settings.width)
		                        .attr('height', settings.height)
		                        .css('cursor','pointer');
			
		    settings.current = 0;
		    settings.loadedAmount = 0;
		    settings.videoPosition = 0;
		    settings.thumbnails$ = this.filter('img');
		    
		    settings.thumbnails$.each(
		    	function(n) {
		    		$(this).data('photo-index', n); 
		    	}
		    ).click(
		    	function() {
		    
		    		if(settings.hasDialog == true) {
		    			$(settings.photoPane).dialog('open');
		    		}
		    	
		    		showVideo($(this).data('photo-index'));
		    	}
		    );

		    
			if(settings.hasDialog == true) { 
				$(settings.videoPane).dialog({
					autoOpen:false, 
					width:settings.dialogWidth,
					title:settings.dialogTitle,
					position:settings.dialogPosition,
					zIndex:settings.zIndex,
					stack:settings.stack,
					create: function(event, ui) {
						$('.ui-dialog-titlebar-close').bind('click', 
							function(event) {							
			        			 var playButton = $(this).parent().find('.ui-dialog-buttonpane button:first-child');
				       	         
			        			 if(playButton.hasClass('isPaused')) {
			        				$("#playButton").removeClass('isPaused'); 
			        			 } 
			        			
			        			 stop();
			        			 
			        			 $("#playButton").button("option", "label", settings.play);
					             $("#playButton").button("option", "icons", {secondary:"ui-icon-play"});
			        			 $(this).dialog("close");
								
								
				    	 	}
						);
		
					},
					create: function() {
						$(this).parent().find('.ui-dialog-buttonpane button:first-child').button({
		        	        icons: { secondary: 'ui-icon-play' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(2)').button({
		        	        icons: { secondary: 'ui-icon-volume-off' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(3)').button({
		        	        icons: { primary: 'ui-icon-seek-first' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(4)').button({
		        	        icons: { primary: 'ui-icon-seek-prev' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(5)').button({
		        	        icons: { secondary: 'ui-icon-seek-next' }
		        	    });
		        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(6)').button({
		        	        icons: { secondary: 'ui-icon-seek-end' }
		        	    });
		        	},
		
		        	buttons: [{
		        		text: settings.play,
		                click: function(event) {

			               	if($("#playButton").hasClass('isPaused')) {
			                	play();
			                	
			                	$("#playButton").removeClass('isPaused');
			                	$("#playButton").addClass('isPlaying');
			               		$("#playButton").button("option", "label", settings.pause);
			               		$("#playButton").button("option", "icons", {secondary:"ui-icon-pause"});
			                } else {
			                	pause();
			                	
			                	$("#playButton").removeClass('isPlaying');
			                	$("#playButton").addClass('isPaused');
			               		$("#playButton").button("option", "label", settings.play);
			               		$("#playButton").button("option", "icons", {secondary:"ui-icon-play"});		               		
			                }	                	
		                	
		                }
		             },
		        	 {
		        		 text: settings.mute,
		        		 click: function() {
		        			 var soundButton = $(this).parent().find('.ui-dialog-buttonpane button:nth-child(2)');
			       	         
				             if(soundButton.hasClass('isMuted')) {
				             	unMuteVideo();
			
				               	soundButton.removeClass('isMuted');
				               	soundButton.button("option", "label", settings.mute);
				               	soundButton.button("option", "icons", {secondary:"ui-icon-volume-off"});
				             } else {
				               	muteVideo();              
				                	
				               	soundButton.addClass('isMuted');
				               	soundButton.button("option", "label", settings.unMute);
				               	soundButton.button("option", "icons", {secondary:"ui-icon-volume-on"});
				             }   
		        		 }		        		 
		        	 },
		             {
		            	 text: settings.first,
		                 click: function() {
		            	     showVideo(0);	 
		            	 }
		             	
		             },
		        	 {
		        		 text: settings.previous,
		        		 click: function() {
		        			 showVideo((settings.thumbnails$.length + settings.current - 1) % settings.thumbnails$.length);
		        		 }
		        	 },
		        	 {
		        		 text: settings.next,
		        		 click: function() {
		        			 showVideo((settings.thumbnails$.length + settings.current + 1) % settings.thumbnails$.length); 
		        	     }
		        	 },
		        	 {
		        		 text: settings.last,
		        		 click: function() {
		        			 showVideo(settings.thumbnails$.length - 1);
		        		 }
		        	 },
		        	 {
		        		 text: settings.close,
		        		 click: function() {
		        			 var playButton = $(this).parent().find('.ui-dialog-buttonpane button:first-child');
			       	         
		        			 if(playButton.hasClass('isPaused')) {
		        				$("#playButton").removeClass('isPaused'); 
		        			 } 
		        			
		        			 stop();
		        			 
		        			 $("#playButton").button("option", "label", settings.play);
				             $("#playButton").button("option", "icons", {secondary:"ui-icon-play"});
		        			 $(this).dialog("close");
		        		 }
		        	 }		        	 
		        	 ]
				 });   
			} else {
				$(settings.videoPane).append(
					'<div id="rushYoutubeVideoShowButtonsPane">' +
						'<div id="playButton"></div>' +
						'<div id="soundButton"></div>' +
						'<div id="firstButton"></div>' +
						'<div id="previousButton"></div>' +
						'<div id="nextButton"></div>' +
						'<div id="lastButton"></div>' +
						'<div id="helpButton"></div>' +
					'</div>'
				);

				
				$("#playButton").button(
					{
		        		label: settings.play,
		                icons: {secondary: 'ui-icon-play'}
					}
				).click(
					function(event) {
						
		               	if($("#playButton").hasClass('isPaused')) {
		                	play();
		                	
		                	$("#playButton").removeClass('isPaused');
		                	$("#playButton").addClass('isPlaying');
		               		$("#playButton").button("option", "label", settings.pause);
		               		$("#playButton").button("option", "icons", {secondary:"ui-icon-pause"});
		                } else if($("#playButton").hasClass('isPlaying')){
		                	pause();
		                	
		                	$("#playButton").removeClass('isPlaying');
		                	$("#playButton").addClass('isPaused');
		               		$("#playButton").button("option", "label", settings.play);
		               		$("#playButton").button("option", "icons", {secondary:"ui-icon-play"});		               		
		                } else {
		                	showVideo(0);
		                }
		               	
		            }
				);
				
				
				$("#soundButton").button(
						{
			        		label: settings.mute,
			                icons: {secondary: 'ui-icon-volume-off'}
						}
					).click(
						function(event) {
		        			var soundButton = $(this); 
							
				             if(soundButton.hasClass('isMuted')) {
				             	unMuteVideo();
			
				               	soundButton.removeClass('isMuted');
				               	soundButton.button("option", "label", settings.mute);
				               	soundButton.button("option", "icons", {secondary:"ui-icon-volume-off"});
				             } else {
				               	muteVideo();              
				                	
				               	soundButton.addClass('isMuted');
				               	soundButton.button("option", "label", settings.unMute);
				               	soundButton.button("option", "icons", {secondary:"ui-icon-volume-on"});
				             }
			               	
			            }
					);
				
				$("#firstButton").button(
					{
						label: settings.first,
						icons: { primary: 'ui-icon-seek-first' }
					}
				).click(
					function() {
						showVideo(0);	 
					}
				);
				
				$("#previousButton").button(
					{
						label: settings.previous,
			        	icons: { primary: 'ui-icon-seek-prev' }
			        }
				).click(
					function() {
						showVideo((settings.thumbnails$.length + settings.current - 1) % settings.thumbnails$.length);
					}
				);
				
				$("#nextButton").button(
					{
						label: settings.next,
			        	icons: { secondary: 'ui-icon-seek-next' }
					}
				).click(
					function() {
						showVideo((settings.thumbnails$.length + settings.current + 1) % settings.thumbnails$.length); 
					}
				);
				
				$("#lastButton").button(
					{
						label: settings.last,
			        	icons: { secondary: 'ui-icon-seek-end' }
					}
				).click(
					function() {
						showVideo(settings.thumbnails$.length - 1);
			        }
				);
				
				$("#helpButton").button(
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
				    		.fadeOut('slow')
				    		.remove();
				    }				    
				);

				
			}
				
		    $(document).bind("keydown",
					
				function(event) {
					var keyCode = (event.keyCode ? event.keyCode : event.which);
					var Arrow = {LEFT: 37, UP: 38, RIGHT: 39, DOWN: 40 };
					var VideoControl = {PLAY: 80, SOUND: 83};
		
					switch (keyCode) {
						case VideoControl.PLAY:
							$("#playButton").triggerHandler("click");
					    	break;
					    case VideoControl.SOUND:
					    	$("#soundButton").triggerHandler("click");
					    	break;				  
					    case Arrow.LEFT:
					    	$("#previousButton").triggerHandler("click");
					    	break;
					    case Arrow.UP:
							$("#firstButton").triggerHandler("click");
							break;
					    case Arrow.RIGHT:
					    	$("#nextButton").triggerHandler("click");
					    	break;
					    case Arrow.DOWN:
							$("#lastButton").triggerHandler("click");
							break;
					}
				}
			);
					

			function showVideo(index) {
			    settings.current = index;      
			    $(settings.videoDescription).html(settings.thumbnails$[index].alt);
			    $(settings.currentVideo).html(settings.video + ' ' + (settings.current + 1) + ' ' + settings.of + ' ' + settings.thumbnails$.length);
			    
               	if($("#playButton").hasClass('isPaused')) {
               		$("#playButton").removeClass('isPaused');
               	}
               	
                $("#playButton").addClass('isPlaying');
               	$("#playButton").button("option", "label", settings.pause);
               	$("#playButton").button("option", "icons", {secondary:"ui-icon-pause"});

				showVideoById(settings.thumbnails$[index].id);
		    }

			function showVideoById(videoId) {
				ytPlayer.loadVideoById(videoId);
				ytPlayer.playVideo();
		    }
			
			function loadVideoById(videoId) {
				ytPlayer.loadVideoById(videoId);
			}
			
			function getState() {
				ytPlayer.getPlayerState();
			}
			
			function setVideoVolume() {
				var volume = parseInt($("#volumeSetting").value);
			  
				if(isNaN(volume) || volume < 0 || volume > 100) {
					$("#errorConsole").html(settings.validVolume);
				}
			  
				else if(ytPlayer) {
					ytPlayer.setVolume(volume);
				}
			  
			}
			
			function muteVideo() {
				
				if(ytPlayer) {
					ytPlayer.mute();
				}
			  
			}

			function unMuteVideo() {
				
				if(ytPlayer) {
					ytPlayer.unMute();
				}
			}
			
			function play() {
				
				if(ytPlayer) {
					ytPlayer.playVideo();
				}
			}
			
			function pause() {
				
				if(ytPlayer) {
					ytPlayer.pauseVideo();
				}
			}
			
			function stop() {
				
				if(ytPlayer) {
					ytPlayer.stopVideo();
				}
			}
			
			function seekTo(position) {
		        var seekToPosition = position * ytPlayer.getDuration() / 100;
		        ytPlayer.seekTo(seekToPosition, false);
		    };
			
			function update() {
	    		  
				if(ytPlayer && ytPlayer.getDuration()) {

					if( ytPlayer.getPlayerState() === 1 ) {
						play();
					} else if ( ytPlayer.getPlayerState() === 0 ) {
						pause();
					}
	            
					if( ytPlayer.getCurrentTime() > 0 ) {
						settings.videoPosition = ytPlayer.getCurrentTime() / ytPlayer.getDuration() * 100;
						$(settings.videoBar).slider("values", 0, settings.videoPosition);
						$("#videoTime").css({fontSize:"xx-small", float:"left", zIndex:1000}).html(ytPlayer.getCurrentTime() + settings.of +  ytPlayer.getDuration() + " seg.");
					}
					
					if( ytPlayer.getVideoBytesLoaded() > -1) {
						settings.loadedAmount = ytPlayer.getVideoBytesLoaded() / ytPlayer.getVideoBytesTotal() * 100;
						$(settings.videoBar).slider("values", 1, settings.loadedAmount);
					}
					
				}

	    	};
	    	
	    	$(settings.videoBar).slider({
	    		orientation:"horizontal", 
	    		range:true, 
	    		animate:false, 
	    		min:0, 
	    		max:100,
	    		step:1,
	    		values: [0,0],
	    		start:function(event, ui) {
	    			window.clearInterval(settings.tick);
	    		},
	    		slide: function(event, ui) {
	    			seekTo(ui.value);
	    		},
	    		stop: function(event, ui) {
	    			settings.tick = window.setInterval(
    					function() {
    						update(); 
    	               }, settings.delay
	    			);
	    		}
	    	}).css({
	    		width:"640px",
	    		marginLeft:"227px",
	    		marginTop:"5px",
	    		marginBottom:"5px"
	    	});
	    	
			update();
			settings.tick = window.setInterval(
				function() {
					update(); 
                }, settings.delay
			);
	    	
	    	/*
			ytPlayer.one(
				"onStateChange", 
				errorCode, 
				function(event) {
					$("#errorConsole").html(errorCode);
				}
			);
			
			ytPlayer.one(
					"onError",
					newState,
					function(event) {
						$("#errorConsole").html(newState);		
					}
			);
			*/


		    return this;
	    };

	})(jQuery);