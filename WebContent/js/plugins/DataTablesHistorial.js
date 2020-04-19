$(document).ready(function() {
	$('table').DataTable({
		"scrollY" : "320px",
		"scrollCollapse" : true,
		"bFilter": false,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        "drawCallback": function() {
            var api = this.api();
            var rowCount = api.rows({page: 'current'}).count();
            
            var difference = api.page.len() - rowCount;

            if( api.page.len()==10 && rowCount < 10) {

                for (var i = 0; i < difference-2 ; i++) {
                    $('tbody').append($("<tr><td class='border-light bg-light'>&nbsp;</td></tr>"));
                  }
            }
            

            
        }
	});
	
	$('.dataTables_length').addClass('bs-select');
});

