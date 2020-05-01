$(document).ready(function() {
	$('table').DataTable({
			"scrollY" : "385px",
			"scrollCollapse" : true,
			"bFilter": false,
			
			"columnDefs": [
				{ "orderable": false, "targets": [3] },
				  {
				     "targets": [0,3], 
				     "className": "text-center",
				 }],
				 
			"lengthChange": false,
			"pageLength": "All",
			"paginate": false,
            "language": {
	            "info": "Mostrando un total de _TOTAL_ partidas creadas.",
		        "emptyTable": "No hay partidas creadas.",
		        "infoEmpty": "No se han encontrado partidas creadas.",
	        },
	        
	        "drawCallback": function() {
	            var api = this.api();
	            var rowCount = api.rows({page: 'current'}).count();
	            
	            if(rowCount < 6) {
	            	
	                for (var i = 0; i <= 5; i++) {
	                	
	                    $('tbody').append($("<tr><td class='border-light bg-light'>&nbsp;</td></tr>"));
	                 }
	            }
	        }
        }
	);
	
	$('.dataTables_length').addClass('bs-select');
});

