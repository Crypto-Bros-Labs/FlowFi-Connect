import { useState, useEffect } from 'react';
import authLocalService from '../../features/login/data/local/authLocalService';

export const useAuth = () => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    useEffect(() => {
        checkAuthStatus();
    }, []);

    const checkAuthStatus = () => {
        try {
            const hasTokens = authLocalService.hasTokens();
            setIsAuthenticated(hasTokens);
        } catch (error) {
            console.error('Error checking auth status:', error);
            setIsAuthenticated(false);
        } finally {
            setIsLoading(false);
        }
    };

    return {
        isAuthenticated,
        isLoading,
        checkAuthStatus
    };
};