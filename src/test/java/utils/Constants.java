package utils;

/**
 * Application Constants
 */
public class Constants {
    
    // URLs
    public static final String BASE_URL = "https://the-internet.herokuapp.com";
    
    // Page URLs
    public static class Urls {
        public static final String ADD_REMOVE_ELEMENTS = BASE_URL + "/add_remove_elements/";
        public static final String BASIC_AUTH = BASE_URL + "/basic_auth";
        public static final String BROKEN_IMAGES = BASE_URL + "/broken_images";
        public static final String CHALLENGING_DOM = BASE_URL + "/challenging_dom";
        public static final String CHECKBOXES = BASE_URL + "/checkboxes";
        public static final String CONTEXT_MENU = BASE_URL + "/context_menu";
        public static final String DIGEST_AUTH = BASE_URL + "/digest_auth";
        public static final String DISAPPEARING_ELEMENTS = BASE_URL + "/disappearing_elements";
        public static final String DRAG_AND_DROP = BASE_URL + "/drag_and_drop";
        public static final String DROPDOWN = BASE_URL + "/dropdown";
        public static final String DYNAMIC_CONTENT = BASE_URL + "/dynamic_content";
        public static final String DYNAMIC_CONTROLS = BASE_URL + "/dynamic_controls";
        public static final String DYNAMIC_LOADING = BASE_URL + "/dynamic_loading";
        public static final String FILE_DOWNLOAD = BASE_URL + "/download";
        public static final String FILE_UPLOAD = BASE_URL + "/upload";
        public static final String FLOATING_MENU = BASE_URL + "/floating_menu";
        public static final String FORGOT_PASSWORD = BASE_URL + "/forgot_password";
        public static final String FORM_AUTHENTICATION = BASE_URL + "/login";
        public static final String FRAMES = BASE_URL + "/frames";
        public static final String GEOLOCATION = BASE_URL + "/geolocation";
        public static final String HORIZONTAL_SLIDER = BASE_URL + "/horizontal_slider";
        public static final String HOVERS = BASE_URL + "/hovers";
        public static final String INFINITE_SCROLL = BASE_URL + "/infinite_scroll";
        public static final String INPUTS = BASE_URL + "/inputs";
        public static final String JQUERY_UI_MENUS = BASE_URL + "/jqueryui/menu";
        public static final String JAVASCRIPT_ALERTS = BASE_URL + "/javascript_alerts";
        public static final String KEY_PRESSES = BASE_URL + "/key_presses";
        public static final String LARGE_DOM = BASE_URL + "/large";
        public static final String MULTIPLE_WINDOWS = BASE_URL + "/windows";
        public static final String NESTED_FRAMES = BASE_URL + "/nested_frames";
        public static final String NOTIFICATION_MESSAGES = BASE_URL + "/notification_message";
        public static final String REDIRECT_LINK = BASE_URL + "/redirector";
        public static final String SECURE_FILE_DOWNLOAD = BASE_URL + "/download_secure";
        public static final String SHADOW_DOM = BASE_URL + "/shadowdom";
        public static final String SHIFTING_CONTENT = BASE_URL + "/shifting_content";
        public static final String SLOW_RESOURCES = BASE_URL + "/slow";
        public static final String SORTABLE_DATA_TABLES = BASE_URL + "/tables";
        public static final String STATUS_CODES = BASE_URL + "/status_codes";
        public static final String TYPOS = BASE_URL + "/typos";
        public static final String WYSIWYG_EDITOR = BASE_URL + "/tinymce";
    }
    
    // Timeouts
    public static class Timeouts {
        public static final int IMPLICIT_WAIT = 10;
        public static final int EXPLICIT_WAIT = 15;
        public static final int PAGE_LOAD_TIMEOUT = 30;
        public static final int SCRIPT_TIMEOUT = 30;
        public static final int SHORT_WAIT = 5;
    }
    
    // Credentials
    public static class Credentials {
        public static final String VALID_USERNAME = "tomsmith";
        public static final String VALID_PASSWORD = "SuperSecretPassword!";
        public static final String INVALID_USERNAME = "invaliduser";
        public static final String INVALID_PASSWORD = "wrongpassword";
    }
    
    // Messages
    public static class Messages {
        public static final String LOGIN_SUCCESS = "You logged into a secure area!";
        public static final String LOGIN_INVALID_USER = "Your username is invalid!";
        public static final String LOGIN_INVALID_PASS = "Your password is invalid!";
        public static final String LOGOUT_SUCCESS = "You logged out of the secure area!";
        public static final String HELLO_WORLD = "Hello World!";
    }
}