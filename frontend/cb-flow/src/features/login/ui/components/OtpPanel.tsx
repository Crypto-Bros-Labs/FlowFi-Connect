import React from 'react';
import ButtonApp from '../../../../shared/components/ButtonApp';
import DescriptionApp from '../../../../shared/components/DescriptionApp';
import HeaderModal from '../../../../shared/components/HeaderModal';
import OtpInput from './OtpInput';
import { useOtp } from '../hooks/useOtp';
import secure from '/illustrations/secure.png';

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
            <div className="w-30 h-30 mx-auto mb-6 flex items-center justify-center">
                <img src={secure} alt="Secure Icon" className="w-full h-full" />
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