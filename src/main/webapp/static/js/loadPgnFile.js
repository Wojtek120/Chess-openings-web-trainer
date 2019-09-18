$(() => {
    $("#loadPgnBtn").on('change', function (e) {

        if (window.File && window.FileReader && window.FileList && window.Blob) {

            const files = this.files;
            const file = files[0];

            console.log(file);
            console.log(file.type);

            if (!file.type.match(".*pgn$")) {
                return;
            }

            readFileContentAndPutItInInput(file);


        } else {
            alert('The file reader are not fully supported in this browser. Please update browser.');
        }
    });


    const readFileContentAndPutItInInput = file => {

        const reader = new FileReader();
        let content;

        reader.onloadend = function (evt) {
            const hiddenInputToPutPgnStringTo = $("#openingPgnString");

            content = evt.target.result;
            console.log("File contents: " + content);

            hiddenInputToPutPgnStringTo.val(content);
        };

        reader.readAsBinaryString(file);

    };
});