var specialElementHandlers = {
    // element with id of "bypass" - jQuery style selector
    '.no-export': function (element, renderer) {
        // true = "handled elsewhere, bypass text extraction"
        return true;
    }
};

 function exportPDF(id) {
    var doc = new jsPDF('p', 'pt', [1500, 1200]);
    //A4 - 595x842 pts
    //https://www.gnu.org/software/gv/manual/html_node/Paper-Keywords-and-paper-size-in-points.html


    //Html source 
    var source = document.getElementById(id);
console.log(source);
   columnStyles: {
  2: {
    columnWidth: 'auto'
  }
}
    var margins = {
        top: 10,
        bottom: 10,
        left: 10,
        width: 1200
    };

               doc.autoTable(res.columns, res.data, {
                addPageContent: pageContent,
                startY: 43,
                margin: {horizontal: 7},
                styles: {overflow: 'linebreak', columnWidth: 'auto'},
                columnStyles: {0: {columnWidth: '15%'}}
            });,

        function (dispose) {
            // dispose: object with X, Y of the last line add to the PDF 
            //          this allow the insertion of new lines after html
			doc.setFont("helvetica");
            doc.setFontType("bold");
            doc.setFontSize(9);
            doc.save('PostList.pdf');
        }, margins);
}