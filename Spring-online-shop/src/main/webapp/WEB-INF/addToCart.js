$(document).ready(function () {
    $('.btn-cart').click(function () {
        let productCode = $(this).val();
        let quantity = $("#quantity").val();
        $.ajax({
            url : "/add_product",
            type : "POST",
            dataType : "TEXT",
            data : {productCode: productCode,
                quantity: quantity},
            success : function (response) {
                alert(response)
            }
        })
    });
})