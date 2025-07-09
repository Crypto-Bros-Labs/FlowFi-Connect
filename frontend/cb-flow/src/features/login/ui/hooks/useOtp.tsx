import { useRef, useState } from 'react';

interface UseOtpProps {
    length?: number;
    onComplete?: (otp: string) => void;
}

export const useOtp = ({ length = 4, onComplete }: UseOtpProps = {}) => {
    //input state
    const [otp, setOtp] = useState<string[]>(Array(length).fill(''));
    const inputRefs = useRef<(HTMLInputElement | null)[]>([]);

    //input handlers
    const handleChange = (index: number, value: string) => {
        // Solo permitir números
        if (value && !/^\d$/.test(value)) return;

        const newOtp = [...otp];
        newOtp[index] = value;
        setOtp(newOtp);

        // Mover al siguiente input si hay un valor
        if (value && index < length - 1) {
            inputRefs.current[index + 1]?.focus();
        }

        // Llamar onComplete cuando todos los campos estén llenos
        if (newOtp.every(digit => digit !== '') && onComplete) {
            onComplete(newOtp.join(''));
        }
    };

    const handleKeyDown = (index: number, e: React.KeyboardEvent<HTMLInputElement>) => {
        // Mover al input anterior con backspace
        if (e.key === 'Backspace' && !otp[index] && index > 0) {
            inputRefs.current[index - 1]?.focus();
        }

        // Mover al siguiente input con la flecha derecha
        if (e.key === 'ArrowRight' && index < length - 1) {
            inputRefs.current[index + 1]?.focus();
        }

        // Mover al input anterior con la flecha izquierda
        if (e.key === 'ArrowLeft' && index > 0) {
            inputRefs.current[index - 1]?.focus();
        }
    };

    const handlePaste = (e: React.ClipboardEvent) => {
        e.preventDefault();
        const pastedData = e.clipboardData.getData('text');
        const digits = pastedData.replace(/\D/g, '').slice(0, length).split('');

        const newOtp = [...otp];
        digits.forEach((digit, index) => {
            if (index < length) {
                newOtp[index] = digit;
            }
        });

        setOtp(newOtp);

        // Enfocar el último input lleno o el siguiente vacío
        const nextIndex = Math.min(digits.length, length - 1);
        inputRefs.current[nextIndex]?.focus();

        // Llamar onComplete si se pegaron todos los dígitos
        if (digits.length === length && onComplete) {
            onComplete(newOtp.join(''));
        }
    };

    const clearOtp = () => {
        setOtp(Array(length).fill(''));
        inputRefs.current[0]?.focus();
    };

    return {
        otp,
        inputRefs,
        handleChange,
        handleKeyDown,
        handlePaste,
        clearOtp,
    };
};