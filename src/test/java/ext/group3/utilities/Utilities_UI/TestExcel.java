package ext.group3.utilities.Utilities_UI;

public class TestExcel {

    public static void main(String[] args) {

        ExcelUtils excelUtils = new ExcelUtils("/Users/annanicol/Documents/QA_Testing2024/AutomationWithSelenium/CucumberProjectB3/cucumber-project-b3/src/test/resources/Sample.xlsx", "Nadir");
        System.out.println("excelUtils.getCellData(1,1) = " + excelUtils.getCellData(1, 1));

        excelUtils.setCellData("Feyruz", 1, 1);
        System.out.println("excelUtils.getCellData(1, 1) = " + excelUtils.getCellData(1, 1));
    }
}
