function getContextPath() {
   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}