export const LOCALHOST_URL = 'http://localhost:8080';
export const FRONTEND_URL = "http://localhost:63342/webDemo/frontend/html/";

export function setBearer(newBearer) {
    localStorage.removeItem("bearer");
    localStorage.setItem("bearer", newBearer);
}

export function getBearer() {
    return localStorage.getItem("bearer");
}