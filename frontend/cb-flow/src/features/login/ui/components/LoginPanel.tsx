import React from 'react';
import ButtonApp from '../../../../shared/components/ButtonApp';
import InputApp from '../../../../shared/components/InputApp';
import DescriptionApp from '../../../../shared/components/DescriptionApp';
import HeaderModal from '../../../../shared/components/HeaderModal';
import { useLogin } from '../hooks/useLogin';

interface LoginPanelProps {
    isModal?: boolean;
    isFlow?: boolean;
}

const LoginPanel: React.FC<LoginPanelProps> = ({ isModal = false, isFlow = false }) => {

    const {
        email,
        isLoading,
        error,
        handleEmailChange,
        handleLogin,
    } = useLogin();

    return (
        <div className="bg-white rounded-[1.25rem] w-full h-[80vh] max-w-md p-4 flex flex-col border-2 border-[#3E5EF5] shadow-lg">

            <HeaderModal isModal={isModal} isFlow={isFlow} />

            {/* Imagen placeholder */}
            <div className="w-20 h-20 bg-gray-200 rounded-full mx-auto mb-6 flex items-center justify-center">
                <svg className="w-10 h-10 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
            </div>

            <DescriptionApp
                title='Inicia sesión'
                description='Inicia sesión con tu correo electrónico enlazado a tu cuenta CB para continuar'
            />

            {/* Input de correo */}
            <div className="mb-6">
                <InputApp
                    label='Correo electronico'
                    type='email'
                    placeholder='ejemplo@correo.com'
                    value={email}
                    onChange={(e) => handleEmailChange(e.target.value)}
                    error={error}
                />
            </div>

            {/* Botón continuar */}
            <div className="mb-6">
                <ButtonApp
                    text="Continuar"
                    paddingVertical="py-2"
                    textSize='text-sm'
                    isMobile={true}
                    onClick={handleLogin}
                    loading={isLoading}
                    loadingText='Verificando...'
                    disabled={isLoading || !!error || !email}
                />
            </div>

            {/* Texto de registro 
            <div className="text-center">
                <p className="text-sm text-gray-600">
                    ¿No tienes cuenta?{' '}
                    <button className="text-[#3E5EF5] font-medium hover:underline">
                        Regístrate
                    </button>
                </p>
            </div>
            */}
        </div>
    );
};

export default LoginPanel;