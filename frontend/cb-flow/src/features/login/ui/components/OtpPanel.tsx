import React from 'react';
import ButtonApp from '../../../../shared/components/ButtonApp';
import DescriptionApp from '../../../../shared/components/DescriptionApp';
import HeaderModal from '../../../../shared/components/HeaderModal';
import OtpInput from './OtpInput';
import { useOtp } from '../hooks/useOtp';

interface OtpPanelProps {
    isModal?: boolean;
    isFlow?: boolean;
}

const OtpPanel: React.FC<OtpPanelProps> = ({ isModal = false, isFlow = false }) => {
    // Configuración del OTP
    const length = 4;
    const onComplete = (code: string) => {
        console.log('OTP completed:', code);
    };

    const {
        isLoading,
        handleLogin
    } = useOtp({ length, onComplete });

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
                title='Ingresa el codigo'
                description='Hemos enviado un código de verificación a tu correo'
            />

            {/* Input de correo */}
            <div className="w-full">
                <OtpInput />
            </div>

            {/* Botón continuar */}
            <div className="mt-auto">
                <ButtonApp
                    text="Continuar"
                    paddingVertical="py-2"
                    textSize='text-sm'
                    isMobile={true}
                    onClick={handleLogin}
                    loading={isLoading}
                    loadingText='Verificando...'
                    disabled={isLoading}
                />
            </div>

        </div>
    );
};

export default OtpPanel;