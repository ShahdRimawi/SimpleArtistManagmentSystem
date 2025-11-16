module Airport {
//	requires javafx.controls;
//	
//	opens application to javafx.graphics, javafx.fxml;
//	
//	
	requires javafx.controls;

	opens application to javafx.graphics, javafx.fxml;
	    requires javafx.fxml;       // FXML support (if you're using FXML)

	    // This opens the 'application' package to JavaFX's 'javafx.base' module

	    // Export the 'application' package to allow usage from other parts of your project
	    exports application;
}
