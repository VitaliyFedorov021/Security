$(document).ready(function () {
    $('.btn-cart').click(function () {
        let d = $(this).val();
        let q = $("#quantity").val();
        $.ajax({
            url : "?command=AddToCart",
            type : "POST",
            dataType : "JSON",
            data : {productCode: d,
                    quantity: q}
        })
        alert("Added");
    });
})



