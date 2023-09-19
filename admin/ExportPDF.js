var specialElementHandlers = {
    
    '.no-export': function (element, renderer) {
    
        return true;
    }
};

 function exportPDF(id) {
    var doc = new jsPDF('p', 'pt', [1500, 1200]);

    var source = document.getElementById(id);
console.log(source);
    var margins = {
        top: 10,
        bottom: 10,
        left: 10,
        width: 1200
    };

    doc.fromHTML(
        source, 
        margins.left,
        margins.top, {
            'width': margins.width,
            'elementHandlers': specialElementHandlers
        },

        function (dispose) {
			doc.setFont("helvetica");
            doc.setFontType("bold");
            doc.setFontSize(9);
            doc.save('POList.pdf');
        }, margins);
}