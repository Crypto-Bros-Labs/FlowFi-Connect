import React from 'react';
import ButtonApp from '../components/ButtonApp';
import LoginPanel from '../../features/login/ui/components/LoginPanel';
import Footer from '../components/Footer';

const LandingPage: React.FC = () => {
    return (
        <div className="min-h-screen">
            {/* Primera sección - Hero */}
            <section className="px-15 py-4">
                <div className="flex flex-col md:flex-row gap-12 items-center">
                    {/* Primera columna - Texto principal */}
                    <div className="flex-1">
                        <h1 className="text-8xl font-bold text-[#020F1E] leading-tight mb-8">
                            Convierte pesos y cripto en <span className="text-[#3E5EF5]">segundos</span>
                        </h1>
                        <p className="text-3xl text-gray-600 mb-8">
                            Con <span className="text-[#3E5EF5]">CB Flow Connect</span>, cualquier plataforma puede ofrecer comprar y retirar MXNB y USDC de forma instantánea, sin complicaciones.
                        </p>
                        <ButtonApp
                            paddingVertical="py-6"
                            paddingHorizontal="px-10"
                            text="Empezar ahora"

                            onClick={() => console.log('Empezar ahora clicked')}
                        />
                    </div>

                    {/* Segunda columna - Texto de prueba */}
                    <div className="flex-1 flex items-center justify-center">
                        <div className='w-85'>
                            <LoginPanel />
                        </div>
                    </div>
                </div>
            </section>

            {/* Segunda sección - Cómo funciona */}
            <section className="px-12 py-16 bg-gray-50">
                <div className="max-w-7xl mx-auto">
                    {/* Encabezado de la sección */}
                    <div className=" mb-12">
                        <h2 className="text-6xl font-bold text-[#020F1E] mb-4">
                            Cómo funciona <span className="text-[#3E5EF5]">CB Flow</span> Connect
                        </h2>
                        <p className="text-4xl text-gray-600 mb-8 font-semibold">
                            <span className="text-[#3E5EF5]">Integra un solo enlace y listo:</span> nosotros nos encargamos del resto.
                        </p>
                        <ButtonApp
                            paddingVertical="py-6"
                            paddingHorizontal="px-10"
                            text="Ver más detalles"
                            stroke={true}
                            textColor='text-[#020F1E]'
                            backgroundColor='bg-[#EAF2FC]'
                            onClick={() => console.log(' clicked')}
                        />

                    </div>

                    {/* Contenido principal */}
                    <div className="flex flex-col lg:flex-row gap-12 items-center mt-16">
                        {/* Columna izquierda - Lista de características */}
                        <div className="flex-1">
                            <div className="space-y-8">
                                <div className="flex items-start gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-2xl font-semibold text-[#020F1E] mb-2">
                                            Creación de cuenta
                                        </h3>
                                        <p className="text-gray-600 text-lg">
                                            Tus usuarios se registran y validan su identidad de forma sencilla y segura.
                                        </p>
                                    </div>
                                </div>

                                <div className="flex items-start gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-2xl font-semibold text-[#020F1E] mb-2">
                                            Compra y vende MXNB y USDC
                                        </h3>
                                        <p className="text-gray-600 text-lg">
                                            Opera en la red de Arbitrum, rápido y con liquidez local.
                                        </p>
                                    </div>
                                </div>

                                <div className="flex items-start gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-2xl font-semibold text-[#020F1E] mb-2">
                                            Transacciones vía SPEI
                                        </h3>
                                        <p className="text-gray-600 text-lg">
                                            Envía o retira a cuentas bancarias mexicanas en minutos.
                                        </p>
                                    </div>
                                </div>

                                <div className="flex items-start gap-4">
                                    <div className="w-2 h-2 bg-[#3E5EF5] rounded-full mt-3 flex-shrink-0"></div>
                                    <div>
                                        <h3 className="text-2xl font-semibold text-[#020F1E] mb-2">
                                            Múltiples cuentas y direcciones
                                        </h3>
                                        <p className="text-gray-600 text-lg">
                                            Tus usuarios pueden guardar varias cuentas bancarias para retiros y varias direcciones de billeteras para compras.
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        {/* Columna derecha - Imagen placeholder */}
                        <div className="flex-1">
                            <div className="w-full h-96 bg-gray-200 rounded-lg flex items-center justify-center">
                                <div className="text-center">
                                    <svg className="w-16 h-16 text-gray-400 mx-auto mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
                                    </svg>
                                    <p className="text-gray-500">Imagen placeholder</p>
                                </div>
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