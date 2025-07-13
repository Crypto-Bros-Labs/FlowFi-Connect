import { useState } from 'react';

interface Token {
    id: string;
    name: string;
    symbol: string;
    network: string;
    icon: string;
    color: string;
}

export const useSelectToken = () => {
    const [selectedToken, setSelectedToken] = useState<string | null>(null);

    const tokens: Token[] = [
        {
            id: 'bitcoin',
            name: 'Bitcoin',
            symbol: 'BTC',
            network: 'Bitcoin',
            icon: '₿',
            color: 'bg-orange-500'
        },
        {
            id: 'ethereum',
            name: 'Ethereum',
            symbol: 'ETH',
            network: 'Ethereum',
            icon: 'Ξ',
            color: 'bg-blue-500'
        },
        {
            id: 'usdc',
            name: 'USD Coin',
            symbol: 'USDC',
            network: 'Ethereum',
            icon: '$',
            color: 'bg-blue-600'
        },
        {
            id: 'usdt',
            name: 'Tether',
            symbol: 'USDT',
            network: 'Ethereum',
            icon: '₮',
            color: 'bg-green-500'
        },
        {
            id: 'bnb',
            name: 'Binance Coin',
            symbol: 'BNB',
            network: 'BSC',
            icon: 'B',
            color: 'bg-yellow-500'
        }
    ];

    const selectToken = (tokenId: string) => {
        setSelectedToken(tokenId);
    };

    const clearSelection = () => {
        setSelectedToken(null);
    };

    const getSelectedToken = () => {
        return tokens.find(token => token.id === selectedToken);
    };

    const handleBuy = () => {
        if (selectedToken) {
            console.log('Comprar', selectedToken);
        }
    };

    const handleSell = () => {
        if (selectedToken) {
            console.log('Vender', selectedToken);
        }
    };

    return {
        tokens,
        selectedToken,
        selectToken,
        clearSelection,
        getSelectedToken,
        handleBuy,
        handleSell
    };
};