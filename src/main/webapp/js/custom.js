$(document).ready( function() {
  $('.js-list-hide').hide()
  $('.js-get-data').on('click', function() {
    $.ajax({

        'url' : 'https://bbtnsf.mybluemix.net/insight?email=rose@pedalflowers.biz',
        'type' : 'GET',
        success : function(data) {
          $('.js-list-hide').show();
          // console.log(data.email)
          // console.log(data.alerts[0].product)
          for ( var i = 0; i <  data.alerts.length; i++ ) {
            // console.log(i)
            $('.js-alert')[i].append(data.alerts[i].product)
            $('.js-prob')[i].append(data.alerts[i].problem)
            $('.js-sug')[i].append(data.alerts[i].suggestion)
            // console.log(data.alerts)
            // if ( data.hasOwnProperty( i ) ) {
            //   console.log(i)
            // }
          }

            // alert(data);
        },
        'error' : function(request,error)
        {
            // alert("Request: "+JSON.stringify(request));
        }
    });
  });
});
