let dataTableTemp = null;

$(document).ready( () =>{
    $.noConflict();
    dataTableTemp = $('#patientTbl').DataTable({
        scrollY: '80vh',
        scrollCollapse: true,
        scrollX: true,
        responsive: true,
        order: [[5, "desc"]],

    });
})
