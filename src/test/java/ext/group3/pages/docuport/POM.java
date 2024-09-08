package ext.group3.pages.docuport;

public class POM {

    private DocuClientPage docuClientPage;
    private DocuLoginPage docuLoginPage;

    public DocuClientPage getDocuClientPage() {
        if(docuClientPage == null) {
            docuClientPage = new DocuClientPage();
        }
        return docuClientPage;
    }

    public DocuLoginPage getDocuLoginPage() {
        if(docuLoginPage == null) {
            docuLoginPage = new DocuLoginPage();
        }
        return docuLoginPage;
    }

}
