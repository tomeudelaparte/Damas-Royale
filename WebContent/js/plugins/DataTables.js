
$(document).ready(function() {
	$('table').DataTable({
		"scrollY" : "320px",
		"scrollCollapse" : true,
		"bFilter": false,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
	});
	$('.dataTables_length').addClass('bs-select');
	
});