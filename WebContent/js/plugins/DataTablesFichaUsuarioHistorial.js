$(document).ready(function() {
	
	// Configuración de la tabla de historial de partidas.
	
	$('table').DataTable({
			"scrollY" : "320px",
			"scrollCollapse" : true,
			"bFilter": false,
			
			"columnDefs": [{ 
				"orderable": false, "targets": 5},
					{
				      "targets": 5, 
				      "className": "text-center",
				      "width": "4%"
				}],
			 
	         "language": {
	        	"emptyTable": "No hay partidas realizadas.",
	        	"info": "Mostrando _START_ a _END_ de un total de _TOTAL_ partidas.",
	        	"infoEmpty": "No se han encontrado partidas realizadas.",
	            "lengthMenu": "Mostrar _MENU_ partidas",
	            "paginate": {
	                "next": "Siguiente",
	                "previous": "Anterior"
	            }},
			 
	        "drawCallback": function() {
	            
	        	// Crea filas para rellenar el espacio.
	        	var api = this.api();
	            var rowCount = api.rows({page: 'current'}).count();
	            
	            var difference = api.page.len() - rowCount;
	            
	            if( api.page.len()==10 && rowCount < 10) {
	
	                for (var i = 0; i < difference-2 ; i++) {
	                    $('tbody').append($("<tr><td class='border-light bg-light'>&nbsp;</td></tr>"));
	                  }
	            }
	    
	        }
        }
	);
	
	$('.dataTables_length').addClass('bs-select');
});

