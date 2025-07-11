import axiosInstance from '../../../../shared/api/axiosService';
import type { LoginResponse, AuthResponse } from '../models/authModel';

class AuthService {
    async login(email: string, code: string): Promise<LoginResponse> {
        const response = await axiosInstance.post('/user/login', { email, code });
        return response.data;
    }

    async getAuthData(userEmail: string): Promise<AuthResponse> {
        const response = await axiosInstance.get(`/auth/${userEmail}`);
        return response.data;
    }
}

export default new AuthService();