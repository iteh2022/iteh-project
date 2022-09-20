import axios from "axios";

export function login(token) {
    console.log(token);
    window.sessionStorage.setItem("nas_user", token);
}

export function logout() {
    window.sessionStorage.removeItem("nas_user");
}

export function isLoggedIn() {
    return window.sessionStorage.getItem("nas_user") === null ? null : window.sessionStorage.getItem("nas_user");
}