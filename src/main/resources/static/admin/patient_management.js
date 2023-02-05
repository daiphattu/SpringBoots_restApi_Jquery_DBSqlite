let dataTablePatient = null;

let ptnObj = {
    id: "",
    namePatient: "",
    genderPatient: "",
    birthdayPatient: "",
    addressPatient: "",
    phonePatient: "",
    idCard: ""
}

$(document).ready( () =>{
    $.noConflict();
    toastrConfig();
    dataTablePatient = $('#patientTbl').DataTable({
//        scrollY: '60vh',
//        scrollCollapse: true,
//        scrollX: true,
//        responsive: true,
//        deferRender: true,
//        scroller: true,
        order: [[0, "desc"]],
        columns: [
                    {data: "id", defaultContent: "-", className: "text-center txt-truncate"},
                    {data: "namePatient", defaultContent: "-", className: " txt-truncate"},
                    {data: "genderPatient", defaultContent: "-", className: "text-center txt-truncate"},
                    {data: "birthdayPatient", defaultContent: "-", className: " text-center txt-truncate"},
                    {data: "addressPatient", defaultContent: "-", className: "text-center txt-truncate"},
                    {data: "phonePatient", defaultContent: "-", className: "text-center txt-truncate"},
                    {className: "text-center"}
                ],
                columnDefs: [
                    {
                        targets: [0,1,3,4,5],
                        render: (data, type, row, meta) => {
                            return data ? '<td" title="' + data + '">' + data + '</td>' : '-';
                        }
                    },
                    {
                        targets: [2],
                        render: (data, type, row, meta) => {
                            return data ? '<td" title="' + data + '">' + (data == 1 ? 'Nam' : 'Nữ') + '</td>' : '-';
                        }
                    },
                    {
                        targets: [6],
                        data: 'id',
                        render: (data, type, row, meta) => {
                            return  '<button type="button" id="update_' + data + '" value="' + data + '" class="btn btn-warning" data-toggle="modal" data-target="#updatePatientMdl">'+
                                     'Update'+
                                    '</button>' + ' ' +
                                     '<button type="button" id="delete_' + data + '" value="' + data + '" class="btn btn-danger" data-toggle="modal" data-target="#deletePatientMdl">'+
                                    'Delete'+
                                    '</button>'+ ' ' +
                                    '<button type="button" id="detail_' + data + '" value="' + data + '" class="btn btn-info" data-toggle="modal" data-target="#">'+
                                    'Detail'+
                                    '</button>';
                        }
                    },
                ],
    });

    getAllActivePatients(dataTablePatient);
    createNwPatient();
    getActionBtnTbl();
})

let getAllActivePatients = (dataTablePatient) => {
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: GET_ALL_ACTIVE_PATIENTS,
        data: "data",
        dataType: "json",
        success: (response) => {
            dataTablePatient.clear();
            dataTablePatient.rows.add(response).draw();
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

let createNwPatient = () =>{
	$("#createPatientMdl").on('click','#createPtnBtn' , (e) => {
		e.preventDefault();
		ptnObj.id = null;
		ptnObj.namePatient = $('#patientName').val();
        ptnObj.genderPatient = $('input[name="gender"]:checked').val();
        ptnObj.birthdayPatient = $('#birthdayPatient').val();
        ptnObj.addressPatient = $('#patientAddress').val();
        ptnObj.phonePatient = $('#patientPhone').val();
        ptnObj.idCard = $('#idCardPatient').val();
		console.log(ptnObj);
		$.ajax({
            type: "POST",
            contentType: "application/json",
            url: CREATE_NEW_PATIENT,
            data: JSON.stringify(ptnObj),
            dataType: "json",
            success: (response) => {
                getAllActivePatients(dataTablePatient);
                clearModalProperties();
                $('#createPatientMdl').modal('hide');
                toastr["success"]("Create patient successfully!", "SUCCESS");

            },
            error: (err) => {
                console.log(err);
                clearModalProperties();
                $('#createPatientMdl').modal('hide');
                toastr["warning"]("Cannot create patient!", "ERROR");
            }
        });
	});
};

let clearModalProperties = () => {
	$('#patientName').val('');
    $('input[name="gender"]:checked').prop('checked', false);;
    $('#birthdayPatient').val('');
    $('#patientAddress').val('');
    $('#patientPhone').val('');
    $('#idCardPatient').val('');
}

let getActionBtnTbl = () =>{
	let ptnId = null;
    let idBtn =null;
	$("#patientTbl").on('click','button' , (e) => {
		e.preventDefault();
		idBtn = e.target.id;
		ptnId = e.target.value;
		if(idBtn.includes(UPDATE_STM)){
			findPatientById(ptnId);
			$('#updatePatientMdl').modal('show');
			updatePatientById(ptnId);
		}else if(idBtn.includes(DELETE_STM)){
			$('#deletePatientMdl').modal('show');
			deletePatientById(ptnId);
		}
	});
}

let findPatientById = (ptnId) => {
    if(ptnId){
		$.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: GET_PATIENT_BY_ID + ptnId,
	        data: "data",
	        dataType: "json",
	        success: (response) => {
	        console.log(response);
                $('#upPatientName').val(response.namePatient);
                $("input[name=gender][value=" + response.genderPatient + "]").prop('checked', true);
                $('#upBirthdayPatient').val(response.birthdayPatient);
                $('#upPatientAddress').val(response.addressPatient);
                $('#upPatientPhone').val(response.phonePatient);
                $('#upIdCardPatient').val(response.idCard);

				$('#updPtn').text(response.namePatient);
				$('#updPtn').attr('name', response.id);
	        },
	        error: (err) => {
	            if (err.status === 0) {
	                toastr["warning"]("DISCONNECTION", "ERROR");
	            }
	        }
    	});
	}
}

let updatePatientById = (ptnId) => {
    if(ptnId){
        $("#updatePatientMdl").on('click','#updatePtnBtn' , (e) => {
            e.stopImmediatePropagation();
            ptnObj.id = ptnId;
            ptnObj.namePatient = $('#upPatientName').val();
            ptnObj.genderPatient = $('input[name="gender"]:checked').val();
            ptnObj.birthdayPatient = $('#upBirthdayPatient').val();
            ptnObj.addressPatient = $('#upPatientAddress').val();
            ptnObj.phonePatient = $('#upPatientPhone').val();
            ptnObj.idCard = $('#upIdCardPatient').val();
            console.log(ptnObj);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: UPDATE_PATIENT_BY_ID + ptnId,
                data: JSON.stringify(ptnObj),
                // headers: { Authorization: 'Bearer ' + TOKEN },
                dataType: "json",
                success: (response) => {
                    clearModalProperties();
                    getAllActivePatients(dataTablePatient);
        //                clearModalProperties();
                    $('#updatePatientMdl').modal('hide');
                    toastr["success"]("Update patient successfully!", "SUCCESS");
                },
                error: (err) => {
                    console.log(err);
                    clearModalProperties();
                    $('#updatePatientMdl').modal('hide');
                    toastr["warning"]("Cannot update patient!", "ERROR");
                }
            });
        });
    }
}

let deletePatientById = (ptnId) => {
    if(ptnId){
        $("#deletePatientMdl").on('click','#deletePtnBtn' , (e) => {
            $.ajax({
                type: "DELETE",
                contentType: "application/json",
                url: DELETE_PATIENT_BY_ID + ptnId,
                data: "data",
                dataType: "json",
                success: (response) => {
                    getAllActivePatients(dataTablePatient);
                    $('#deletePatientMdl').modal('hide');
                    toastr["success"]("Delete patient successfully!", "SUCCESS");
                },
                error: (err) => {
                    console.log(err);
                    if (err.status === 200) {
                        getAllActivePatients(dataTablePatient);
                        $('#deletePatientMdl').modal('hide');
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
