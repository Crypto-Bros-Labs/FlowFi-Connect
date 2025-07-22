import React from 'react';
import { useNavigate } from 'react-router-dom';
import ButtonApp from '../components/ButtonApp';
import Footer from '../components/Footer';
import OnOffRampPanel from '../../features/exchange/ui/components/OnOffRampPanel';

const LandingPage: React.FC = () => {
    const navigate = useNavigate();

    const handleStartNow = () => {
        navigate('/on-off-ramp');
    };

    const handleSeeDetails = () => {
        navigate('/login');
    };

    return (
        <div className="min-h-screen">
            {/* Primera sección - Hero */}
            <section className="px-4 sm:px-6 md:px-8 lg:px-12 xl:px-15 py-4 sm:py-6 md:py-8">
                <div className="flex flex-col lg:flex-row gap-6 sm:gap-8 md:gap-12 items-center max-w-7xl mx-auto">
                    {/* Primera columna - Texto principal */}
                    <div className="flex-1 text-center lg:text-left">
                        <h1 className="text-3xl sm:text-4xl md:text-5xl lg:text-6xl xl:text-8xl font-bold text-[#020F1E] leading-tight mb-4 sm:mb-6 md:mb-8">
                            Convierte pesos y cripto en <span className="text-[#3E5EF5]">segundos</span>
                        </h1>
                        <p className="text-base sm:text-lg md:text-xl lg:text-2xl xl:text-3xl text-gray-600 mb-6 sm:mb-8 max-w-2xl mx-auto lg:mx-0">
                            Con <span className="text-[#3E5EF5]">CB Flow Connect</span>, cualquier plataforma puede ofrecer comprar y retirar USDC de forma instantánea, sin complicaciones.
                        </p>
                        <div className="flex justify-center lg:justify-start">
                            <ButtonApp
                                paddingVertical="py-3 sm:py-4 md:py-6"
                                paddingHorizontal="px-6 sm:px-8 md:px-10"
                                text="Empezar ahora"
                                textSize="text-sm sm:text-base md:text-lg"
                                onClick={handleStartNow}
                            />
                        </div>
                    </div>

                    {/* Segunda columna - Login Panel */}
                    <div className="flex-1 flex items-center justify-center w-full">
                        <div className="w-full max-w-sm sm:max-w-md md:max-w-lg lg:max-w-xl xl:w-85">
                            <OnOffRampPanel />
                        </div>
                    </div>
                </div>
            </section>

            {/* Segunda sección - Cómo funciona */}
            <section className="px-4 sm:px-6 md:px-8 lg:px-12 py-8 sm:py-12 md:py-16 bg-gray-50">
                <div className="max-w-7xl mx-auto">
                    {/* Encabezado de la sección */}
                    <div className="text-center lg:text-left mb-8 sm:mb-10 md:mb-12">
                        <h2 className="text-2xl sm:text-3xl md:text-4xl lg:text-5xl xl:text-6xl font-bold text-[#020F1E] mb-3 sm:mb-4">
                            Cómo funciona <span className="text-[#3E5EF5]">CB Flow</span> Connect
                        </h2>
                        <p className="text-lg sm:text-xl md:text-2xl lg:text-3xl xl:text-4xl text-gray-600 mb-6 sm:mb-8 font-semibold max-w-4xl mx-auto lg:mx-0">
                            <span className="text-[#3E5EF5]">Integra un solo enlace y listo:</span> nosotros nos encargamos del resto.
                        </p>
                        <div className="flex justify-center lg:justify-start">
                            <ButtonApp
                                paddingVertical="py-3 sm:py-4 md:py-6"
                                paddingHorizontal="px-6 sm:px-8 md:px-10"
                                text="Ver más detalles"
                                textSize="text-sm sm:text-base md:text-lg"
                                stroke={true}
                                textColor='text-[#020F1E]'
                                backgroundColor='bg-[#EAF2FC]'
                                onClick={handleSeeDetails}
                            />
                        </div>
                    </div>

                    {/* Contenido principal - resto del código igual... */}
                    <div className="flex flex-col xl:flex-row gap-8 sm:gap-10 md:gap-12 items-center mt-8 sm:mt-12 md:mt-16">
                        {/* Columna izquierda - Lista de características */}
                        <div className="flex-1 w-full">
                            <div className="space-y-6 sm:space-y-8">
                                <div className="flex items-start gap-3 sm:gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-2 sm:mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-lg sm:text-xl md:text-2xl font-semibold text-[#020F1E] mb-1 sm:mb-2">
                                            Creación de cuenta
                                        </h3>
                                        <p className="text-gray-600 text-sm sm:text-base md:text-lg">
                                            Tus usuarios se registran y validan su identidad de forma sencilla y segura.
                                        </p>
                                    </div>
                                </div>

                                <div className="flex items-start gap-3 sm:gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-2 sm:mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-lg sm:text-xl md:text-2xl font-semibold text-[#020F1E] mb-1 sm:mb-2">
                                            Compra y vende MXNB y USDC
                                        </h3>
                                        <p className="text-gray-600 text-sm sm:text-base md:text-lg">
                                            Opera en la red de Arbitrum, rápido y con liquidez local.
                                        </p>
                                    </div>
                                </div>

                                <div className="flex items-start gap-3 sm:gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-2 sm:mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-lg sm:text-xl md:text-2xl font-semibold text-[#020F1E] mb-1 sm:mb-2">
                                            Transacciones vía SPEI
                                        </h3>
                                        <p className="text-gray-600 text-sm sm:text-base md:text-lg">
                                            Envía o retira a cuentas bancarias mexicanas en minutos.
                                        </p>
                                    </div>
                                </div>

                                <div className="flex items-start gap-3 sm:gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-2 sm:mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-lg sm:text-xl md:text-2xl font-semibold text-[#020F1E] mb-1 sm:mb-2">
                                            Múltiples cuentas y direcciones
                                        </h3>
                                        <p className="text-gray-600 text-sm sm:text-base md:text-lg">
                                            Tus usuarios pueden guardar varias cuentas bancarias para retiros y varias direcciones de billeteras para compras.
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Columna derecha - Imagen placeholder */}
                        <div className="flex-1 w-full">
                            <div className="w-full h-64 sm:h-80 md:h-96 rounded-lg flex items-center justify-center">
                                <img src="/illustrations/cryp.png" alt="Placeholder" className="max-w-full max-h-full object-cover rounded-lg" />
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </div>
    );
};

export default LandingPage;
