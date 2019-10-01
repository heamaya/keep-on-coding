(function($) {
	$.fn.suggester = function(text, options) {
		var settings = $.extend({
			addClass:null,
			origin: {top:0,left:0},
			text: text
		},options||{});
		 
		(this).click(function(event) {
		
			$('<div.suggester>').remove();
			$('<div>').addClass('suggester' + (settings.addClass ? (' ') + settings.addClass : ''))
					  .addClass('ui-widget')
			          .addClass('ui-state-highlight') 
			          .addClass('ui-corner-all')
						  .css({
							  position: 'absolute',
							  top: event.pageY - settings.origin.top,
							  left: event.pageX - settings.origin.left,
							  width: '256px',
							  height: '104px'								
						  })
						  .click(function(event){
							  $(this).fadeOut('slow');
						  })
						  .html(settings.helpText)
						  .appendTo('body').fadeIn('slow');
		});
		
		return this;
	};
})(jQuery);
