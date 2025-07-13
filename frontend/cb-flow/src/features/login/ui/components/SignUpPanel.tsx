import React from "react";
import HeaderModal from "../../../../shared/components/HeaderModal";
import ButtonApp from "../../../../shared/components/ButtonApp";
import InputApp from "../../../../shared/components/InputApp";
import DescriptionApp from "../../../../shared/components/DescriptionApp";
import { useSignup } from "../hooks/useSignup";

interface SignUpPanelProps {
    isModal?: boolean;
    isFlow?: boolean;
}

const SignUpPanel: React.FC<SignUpPanelProps> = ({ isModal = false, isFlow = false }) => {
    const {
        fullname,
        fullnameError,
        email,
        phone,
        phoneError,
        countryCode,
        countryCodeError,
        isLoading,
        error,
        handleFullnameChange,
        handleEmailChange,
        handlePhoneChange,
        handleCountryCodeChange,
        handleUpdateUser,
    } = useSignup();

    return (
        <div className="bg-white rounded-[1.25rem] w-full h-13/16 md:h-[80vh] max-w-md p-4 flex flex-col border-2 border-[#3E5EF5] shadow-lg">

            <HeaderModal isModal={isModal} isFlow={isFlow} />

            {/* Imagen placeholder */}
            <div className="w-20 h-20 bg-gray-200 rounded-full mx-auto mb-6 flex items-center justify-center">
                <svg className="w-10 h-10 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
            </div>

            <DescriptionApp
                title='Crea tu cuenta CB'
                description='Crea tu cuenta para comprar y vender cripto rapidamente'
            />

            {/* Input de nombre */}
            <div className="mb-3">
                <InputApp
                    label='Nombre completo'
                    type='text'
                    placeholder='Nombre Apellido'
                    value={fullname}
                    onChange={(e) => handleFullnameChange(e.target.value)}
                    error={fullnameError}
                    disabled={isLoading}
                />
            </div>

            {/* Input de correo */}
            <div className="mb-3">
                <InputApp
                    label='Correo electronico'
                    type='email'
                    placeholder='ejemplo@correo.com'
                    value={email}
                    onChange={(e) => handleEmailChange(e.target.value)}
                    disabled={true}
                />
            </div>

            {/* Input de telefono */}
            <div className="w-full mb-6">
                <label className="block text-sm font-medium text-[#020F1E] mb-2">
                    Teléfono
                </label>
                <div className="flex gap-2">
                    <div className="w-1/4">
                        <InputApp
                            showLabel={false}
                            type='tel'
                            placeholder='+52'
                            value={countryCode}
                            onChange={(e) => handleCountryCodeChange(e.target.value)}
                            error={countryCodeError}
                            className="text-center"
                            disabled={isLoading}
                        />
                    </div>
                    <div className="w-3/4">
                        <InputApp
                            showLabel={false}
                            type='tel'
                            placeholder='96 1359 9611'
                            value={phone}
                            onChange={(e) => handlePhoneChange(e.target.value)}
                            error={phoneError}
                            disabled={isLoading}
                        />
                    </div>
                </div>

            </div>

            {/* Mensaje de error */}
            {error && (
                <div className="mb-3 flex items-center gap-1">
                    <svg
                        className="w-4 h-4 text-red-500 flex-shrink-0"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                    >
                        <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            strokeWidth={2}
                            d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                        />
                    </svg>
                    <p className="text-sm text-red-500">
                        {error}
                    </p>
                </div>
            )}

            {/* Botón continuar */}
            <div className="mb-3 mt-auto">
                <ButtonApp
                    text="Crear cuenta"
                    paddingVertical="py-2"
                    textSize='text-sm'
                    isMobile={true}
                    onClick={handleUpdateUser}
                    loading={isLoading}
                    loadingText='Creando cuenta...'
                    disabled={isLoading || !fullname || !phone || !countryCode}
                />
            </div>
        </div>
    )
}

export default SignUpPanel; 