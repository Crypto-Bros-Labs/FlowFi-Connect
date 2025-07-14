import { useState, useEffect, useCallback } from 'react';

interface Token {
    id: string;
    name: string;
    symbol: string;
    network: string;
    iconUrl: string; // Cambio de icon a iconUrl
}

interface Currency {
    id: string;
    symbol: string;
    name: string;
    country: string;
    iconUrl: string;
}

export const useSelectToken = () => {
    // Estados para tokens
    const [tokens, setTokens] = useState<Token[]>([]);
    const [selectedToken, setSelectedToken] = useState<string | null>(null);
    const [tokensError, setTokensError] = useState<string | null>(null);

    // Estados para currencies
    const [currencies, setCurrencies] = useState<Currency[]>([]);
    const [selectedCurrency, setSelectedCurrency] = useState<string | null>(null);
    const [currenciesError, setCurrenciesError] = useState<string | null>(null);

    // Estado general
    const [isLoading, setIsLoading] = useState(true);

    // Función para obtener tokens
    const fetchTokens = useCallback(async (): Promise<Token[]> => {
        try {
            // Simular API call
            await new Promise(resolve => setTimeout(resolve, 1500));

            // Mock data con URLs de imágenes reales
            const mockTokens: Token[] = [
                {
                    id: 'bitcoin',
                    name: 'Bitcoin',
                    symbol: 'BTC',
                    network: 'Bitcoin',
                    iconUrl: 'https://cryptologos.cc/logos/bitcoin-btc-logo.png',
                },
                {
                    id: 'ethereum',
                    name: 'Ethereum',
                    symbol: 'ETH',
                    network: 'Ethereum',
                    iconUrl: 'https://cryptologos.cc/logos/ethereum-eth-logo.png',
                },
                {
                    id: 'usdc-ethereum',
                    name: 'USD Coin',
                    symbol: 'USDC',
                    network: 'Ethereum',
                    iconUrl: 'https://cryptologos.cc/logos/usd-coin-usdc-logo.png',
                },
                {
                    id: 'usdc-arbitrum',
                    name: 'USD Coin',
                    symbol: 'USDC',
                    network: 'Arbitrum',
                    iconUrl: 'https://cryptologos.cc/logos/usd-coin-usdc-logo.png',
                },
                {
                    id: 'usdt-ethereum',
                    name: 'Tether',
                    symbol: 'USDT',
                    network: 'Ethereum',
                    iconUrl: 'https://cryptologos.cc/logos/tether-usdt-logo.png',
                },
                {
                    id: 'bnb',
                    name: 'BNB',
                    symbol: 'BNB',
                    network: 'BSC',
                    iconUrl: 'https://cryptologos.cc/logos/bnb-bnb-logo.png',
                },
                {
                    id: 'matic',
                    name: 'Polygon',
                    symbol: 'MATIC',
                    network: 'Polygon',
                    iconUrl: 'https://cryptologos.cc/logos/polygon-matic-logo.png',

                }
            ];

            return mockTokens;
        } catch (error) {
            throw new Error('Failed to fetch tokens ' + (error instanceof Error ? error.message : 'Unknown error'));
        }
    }, []);

    // Función para obtener currencies
    const fetchCurrencies = useCallback(async (): Promise<Currency[]> => {
        try {
            // Simular API call
            await new Promise(resolve => setTimeout(resolve, 1200));

            // Mock data con URLs de banderas reales
            const mockCurrencies: Currency[] = [
                {
                    id: 'mxn',
                    symbol: 'MXN',
                    name: 'Peso Mexicano',
                    country: 'México',
                    iconUrl: 'https://flagcdn.com/w40/mx.png',
                },
                {
                    id: 'usd',
                    symbol: 'USD',
                    name: 'Dólar Estadounidense',
                    country: 'Estados Unidos',
                    iconUrl: 'https://flagcdn.com/w40/us.png',
                },
                {
                    id: 'eur',
                    symbol: 'EUR',
                    name: 'Euro',
                    country: 'Zona Euro',
                    iconUrl: 'https://flagcdn.com/w40/eu.png',
                },
                {
                    id: 'cop',
                    symbol: 'COP',
                    name: 'Peso Colombiano',
                    country: 'Colombia',
                    iconUrl: 'https://flagcdn.com/w40/co.png',
                },
                {
                    id: 'brl',
                    symbol: 'BRL',
                    name: 'Real Brasileño',
                    country: 'Brasil',
                    iconUrl: 'https://flagcdn.com/w40/br.png',
                },
                {
                    id: 'ars',
                    symbol: 'ARS',
                    name: 'Peso Argentino',
                    country: 'Argentina',
                    iconUrl: 'https://flagcdn.com/w40/ar.png',
                }
            ];

            return mockCurrencies;
        } catch (error) {
            throw new Error('Failed to fetch currencies' + (error instanceof Error ? error.message : 'Unknown error'));
        }
    }, []);

    // Cargar tokens
    const loadTokens = useCallback(async () => {
        try {
            setTokensError(null);

            const fetchedTokens = await fetchTokens();
            setTokens(fetchedTokens);

            // Seleccionar Bitcoin por defecto
            if (fetchedTokens.length > 0) {
                setSelectedToken(fetchedTokens[0].id);
            }
        } catch (error) {
            setTokensError(error instanceof Error ? error.message : 'Error loading tokens');
            console.error('Error fetching tokens:', error);
        }
    }, [fetchTokens]);

    // Cargar currencies
    const loadCurrencies = useCallback(async () => {
        try {
            setCurrenciesError(null);

            const fetchedCurrencies = await fetchCurrencies();
            setCurrencies(fetchedCurrencies);

            // Seleccionar MXN por defecto
            const mxnCurrency = fetchedCurrencies.find(c => c.symbol === 'MXN');
            if (mxnCurrency) {
                setSelectedCurrency(mxnCurrency.id);
            }
        } catch (error) {
            setCurrenciesError(error instanceof Error ? error.message : 'Error loading currencies');
            console.error('Error fetching currencies:', error);
        }
    }, [fetchCurrencies]);

    const loadInitialData = useCallback(async () => {
        setIsLoading(true);

        // Cargar en paralelo
        await Promise.all([
            loadTokens(),
            loadCurrencies()
        ]);

        setIsLoading(false);
    }, [loadCurrencies, loadTokens]);

    // Cargar datos iniciales
    useEffect(() => {
        loadInitialData();
    }, [loadInitialData]);

    // Funciones de selección
    const selectToken = (tokenId: string) => {
        setSelectedToken(tokenId);
    };

    const selectCurrency = (currencyId: string) => {
        setSelectedCurrency(currencyId);
    };

    const clearTokenSelection = () => {
        setSelectedToken(null);
    };

    const clearCurrencySelection = () => {
        setSelectedCurrency(null);
    };

    // Getters
    const getSelectedToken = (): Token | undefined => {
        return tokens.find(token => token.id === selectedToken);
    };

    const getSelectedCurrency = (): Currency | undefined => {
        return currencies.find(currency => currency.id === selectedCurrency);
    };


    // Funciones de acción
    const handleBuy = () => {
        const token = getSelectedToken();
        const currency = getSelectedCurrency();

        if (token && currency) {
            console.log('Comprar:', {
                token: token.symbol,
                network: token.network,
                currency: currency.symbol,
                timestamp: new Date().toISOString()
            });
            // Aquí podrías navegar a la página de compra
            // navigate('/buy', { state: { token, currency } });
        } else {
            console.warn('Token o currency no seleccionados');
        }
    };

    const handleSell = () => {
        const token = getSelectedToken();
        const currency = getSelectedCurrency();

        if (token && currency) {
            console.log('Vender:', {
                token: token.symbol,
                network: token.network,
                currency: currency.symbol,
                timestamp: new Date().toISOString()
            });
            // Aquí podrías navegar a la página de venta
            // navigate('/sell', { state: { token, currency } });
        } else {
            console.warn('Token o currency no seleccionados');
        }
    };

    // Funciones de retry
    const retryTokens = () => {
        loadTokens();
    };

    const retryCurrencies = () => {
        loadCurrencies();
    };

    return {
        // Tokens
        tokens,
        selectedToken,
        tokensError,
        selectToken,
        clearTokenSelection,
        getSelectedToken,
        retryTokens,

        // Currencies
        currencies,
        selectedCurrency,
        currenciesError,
        selectCurrency,
        clearCurrencySelection,
        getSelectedCurrency,
        retryCurrencies,

        // General
        isLoading,

        // Actions
        handleBuy,
        handleSell
    };
};