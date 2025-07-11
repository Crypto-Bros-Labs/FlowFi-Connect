import axios from 'axios';

const baseURL = import.meta.env.VITE_API_TEST_URL;

// Instancia con autenticación
const axiosInstance = axios.create({
    baseURL,
    headers: {
        'Content-Type': 'application/json',
    },
});

axiosInstance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

// Instancia pública
const publicAxiosInstance = axios.create({
    baseURL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Solo named exports
export { axiosInstance, publicAxiosInstance };