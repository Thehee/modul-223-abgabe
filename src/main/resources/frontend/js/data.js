export const LOCALHOST_URL = 'http://localhost:8080';

export function setBearer(newBearer) {
    localStorage.removeItem("bearer");
    localStorage.setItem("bearer", newBearer);
}

export function getBearer() {
    return localStorage.getItem("bearer");
}