$(document).ready(function() {
	$('table').DataTable({
		"scrollY" : "320px",
		"scrollCollapse" : true,
		"bFilter": false,
		"lengthChange": false,
		"pageLength": "All",
		"bPaginate": false,
           "oLanguage": {
            "sInfo": "Mostrando un total de _TOTAL_ partidas"
        },
        "drawCallback": function() {
            var api = this.api();
            var rowCount = api.rows({page: 'current'}).count();
            
            if(rowCount < 6) {
            	
                for (var i = 0; i <= 5 ; i++) {
                	
                    $('tbody').append($("<tr><td class='border-light bg-light'>&nbsp;</td></tr>"));
                  }
            }
            

            
        }
	});
	
	$('.dataTables_length').addClass('bs-select');
});

