let dataTableMedicine = null;

let mdcObj = {
    id: "",
    nameOfMedicine: "",
    effectOfMedicine: "",
    priceOfMedicine: "",
    quantity: "",
    producer: ""
}

$(document).ready( () =>{
    $.noConflict();
    toastrConfig();
    dataTableMedicine = $('#medicineTbl').DataTable({
//        scrollY: '60vh',
//        scrollCollapse: true,
//        scrollX: true,
//        responsive: true,
//        deferRender: true,
//        scroller: true,
        order: [[0, "desc"]],
        columns: [
                    {data: "id", defaultContent: "-", className: "text-center txt-truncate"},
                    {data: "nameOfMedicine", defaultContent: "-", className: " txt-truncate"},
                    {data: "effectOfMedicine", defaultContent: "-", className: "text-center txt-truncate"},
                    {data: "priceOfMedicine", defaultContent: "-", className: " text-center txt-truncate"},
                    {data: "quantity", defaultContent: "-", className: "text-center txt-truncate"},
                    {data: "producer", defaultContent: "-", className: "text-center txt-truncate"},
                    {className: "text-center"}
                ],
                columnDefs: [
                    {
                        targets: [0,1,2,3,4,5],
                        render: (data, type, row, meta) => {
                            return data ? '<td" title="' + data + '">' + data + '</td>' : '-';
                        }
                    },
                    {
                        targets: [6],
                        data: 'id',
                        render: (data, type, row, meta) => {
                            return  '<button type="button" id="update_' + data + '" value="' + data + '" class="btn btn-warning" data-toggle="modal" data-target="#updateMedicineMdl">'+
                                     'Update'+
                                    '</button>' + ' ' +
                                     '<button type="button" id="delete_' + data + '" value="' + data + '" class="btn btn-danger" data-toggle="modal" data-target="#deleteMedicineMdl">'+
                                    'Delete'+
                                    '</button>'+ ' ' +
                                    '<button type="button" id="detail_' + data + '" value="' + data + '" class="btn btn-info" data-toggle="modal" data-target="#">'+
                                    'Detail'+
                                    '</button>';
                        }
                    },
                ],
    });

    getAllActiveMedicines(dataTableMedicine);
    createNwMedicine();
    getActionBtnTbl();
})

let getAllActiveMedicines = (dataTableMedicine) => {
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: GET_ALL_ACTIVE_MEDICINE,
        data: "data",
        dataType: "json",
        success: (response) => {
            dataTableMedicine.clear();
            dataTableMedicine.rows.add(response).draw();
            toastr["success"]("SUCCESS", "Successfully");
        },
        error: (err) => {
            if (err.status === 0) {
                toastr["warning"]("DISCONNECTION", "ERROR");
            }else{
                toastr["warning"](err, "ERROR");
            }
        }
    });
};

let createNwMedicine = () =>{
	$("#createMedicineMdl").on('click','#createMdcBtn' , (e) => {
		e.preventDefault();
		mdcObj.id = null;
		mdcObj.nameOfMedicine = $('#nameOfMedicine').val();
        mdcObj.effectOfMedicine = $('#effectOfMedicine').val();
        mdcObj.priceOfMedicine = $('#price').val();
        mdcObj.quantity = $('#quantity').val();
        mdcObj.producer = $('#producer').val();
		console.log(mdcObj);
		$.ajax({
            type: "POST",
            contentType: "application/json",
            url: CREATE_NEW_MEDICINE,
            data: JSON.stringify(mdcObj),
            dataType: "json",
            success: (response) => {
                getAllActiveMedicines(dataTableMedicine);
                clearModalProperties();
                $('#createMedicineMdl').modal('hide');
                toastr["success"]("Create medicine successfully!", "SUCCESS");

            },
            error: (err) => {
                console.log(err);
                clearModalProperties();
                $('#createMedicineMdl').modal('hide');
                toastr["warning"]("Cannot create medicine!", "ERROR");
            }
        });
	});
};

let clearModalProperties = () => {
	$('#nameOfMedicine').val('');
    $('#effectOfMedicine').val('');
    $('#price').val('');
    $('#quantity').val('');
    $('#producer').val('');
}

let getActionBtnTbl = () =>{
	let ptnId = null;
    let idBtn =null;
	$("#medicineTbl").on('click','button' , (e) => {
		e.preventDefault();
		idBtn = e.target.id;
		mdcId = e.target.value;
		if(idBtn.includes(UPDATE_STM)){
			findMedicineById(mdcId);
			$('#updateMedicineMdl').modal('show');
			updateMedicineById(mdcId);
		}else if(idBtn.includes(DELETE_STM)){
			$('#deleteMedicineMdl').modal('show');
			deleteMedicineById(mdcId);
		}
	});
}

let findMedicineById = (mdcId) => {
    if(mdcId){
		$.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: GET_MEDICINE_BY_ID + mdcId,
	        data: "data",
	        dataType: "json",
	        success: (response) => {
                $('#upNameOfMedicine').val(response.nameOfMedicine);
                $('#upEffectOfMedicine').val(response.effectOfMedicine);
                $('#upPrice').val(response.priceOfMedicine);
                $('#upQuantity').val(response.quantity);
                $('#upProducer').val(response.producer);

				$('#updMdc').text(response.nameOfMedicine);
				$('#updMdc').attr('name', response.id);
	        },
	        error: (err) => {
	            if (err.status === 0) {
	                toastr["warning"]("DISCONNECTION", "ERROR");
	            }
	        }
    	});
	}
}

let updateMedicineById = (mdcId) => {
    if(mdcId){
        $("#updateMedicineMdl").on('click','#updateMdcBtn' , (e) => {
            e.stopImmediatePropagation();
            mdcObj.id = mdcId;
            mdcObj.nameOfMedicine = $('#upNameOfMedicine').val();
            mdcObj.effectOfMedicine = $('#upEffectOfMedicine').val();
            mdcObj.priceOfMedicine = $('#upPrice').val();
            mdcObj.quantity = $('#upQuantity').val();
            mdcObj.producer = $('#upProducer').val();
            console.log(mdcObj);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: UPDATE_MEDICINE_BY_ID + mdcId,
                data: JSON.stringify(mdcObj),
                // headers: { Authorization: 'Bearer ' + TOKEN },
                dataType: "json",
                success: (response) => {
                    $('#upNameOfMedicine').val('');
                    $('#upEffectOfMedicine').val('');
                    $('#upPrice').val('');
                    $('#upQuantity').val('');
                    $('#upProducer').val('');

                    getAllActiveMedicines(dataTableMedicine);
                    $('#updateMedicineMdl').modal('hide');
                    toastr["success"]("Update medicine successfully!", "SUCCESS");
                },
                error: (err) => {
                    console.log(err);
                    $('#upNameOfMedicine').val('');
                    $('#upEffectOfMedicine').val('');
                    $('#upPrice').val('');
                    $('#upQuantity').val('');
                    $('#upProducer').val('');

                    $('#updateMedicineMdl').modal('hide');
                    toastr["warning"]("Cannot update patient!", "ERROR");
                }
            });
        });
    }
}
let deleteMedicineById = (mdcId) => {
    if(mdcId){
        $("#deleteMedicineMdl").on('click','#deleteMdcBtn' , (e) => {
            $.ajax({
                type: "DELETE",
                contentType: "application/json",
                url: DELETE_MEDICINE_BY_ID + mdcId,
                data: "data",
                dataType: "json",
                success: (response) => {
                    getAllActiveMedicines(dataTableMedicine);
                    $('#deleteMedicineMdl').modal('hide');
                    toastr["success"]("Delete medicine successfully!", "SUCCESS");
                },
                error: (err) => {
                    console.log(err);
                    if (err.status === 200) {
                        getAllActiveMedicines(dataTableMedicine);
                        $('#deleteMedicineMdl').modal('hide');
                        toastr["success"](err.responseText, "SUCCESS");
                    } else if (err.status === 400) {
                        toastr["warning"](err.responseJSON.message, "ERROR");
                    } else {
                        toastr["warning"](err.responseJSON.message, "ERROR");
                    }
                }
            });
        });
    }
}
