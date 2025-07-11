import { create } from 'zustand';

interface UserState {
    userUuid: string | null;
    hasAllData: boolean | null;
    email: string | null;
    phone: string | null;
    fullName: string | null;
    setAuthData: (data: {
        userUuid: string;
        hasAllData: boolean;
        email: string;
    }) => void;
    setProfileData: (data: {
        phone: string;
        fullName: string;
    }) => void;
    clearUser: () => void;
}

const useUserStore = create<UserState>((set) => ({
    userUuid: null,
    hasAllData: null,
    email: null,
    phone: null,
    fullName: null,

    setAuthData: (data) => set({
        userUuid: data.userUuid,
        hasAllData: data.hasAllData,
        email: data.email
    }),

    setProfileData: (data) => set({
        phone: data.phone,
        fullName: data.fullName
    }),

    clearUser: () => set({
        userUuid: null,
        hasAllData: null,
        email: null,
        phone: null,
        fullName: null
    }),
}));

class UserLocalService {
    private getState() {
        return useUserStore.getState();
    }

    setAuthData(data: { userUuid: string; hasAllData: boolean; email: string }): void {
        this.getState().setAuthData(data);
    }

    setProfileData(data: { phone: string; fullName: string }): void {
        this.getState().setProfileData(data);
    }

    clearUser(): void {
        this.getState().clearUser();
    }

    getUserData(): Pick<UserState, 'userUuid' | 'hasAllData' | 'email' | 'phone' | 'fullName'> {
        const state = this.getState();
        return {
            userUuid: state.userUuid,
            hasAllData: state.hasAllData,
            email: state.email,
            phone: state.phone,
            fullName: state.fullName,
        };
    }

    // Selectores específicos
    hasUserData(): boolean {
        return !!this.getState().userUuid;
    }
}

export default new UserLocalService();