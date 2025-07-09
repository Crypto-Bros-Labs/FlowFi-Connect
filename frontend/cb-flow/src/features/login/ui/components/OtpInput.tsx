import React from 'react';
import { useOtp } from '../hooks/useOtp';

interface OtpInputProps {
    onComplete?: (otp: string) => void;
    className?: string;
    length?: number;
}

const OtpInput: React.FC<OtpInputProps> = ({
    onComplete,
    className = '',
    length = 4
}) => {
    const {
        otp,
        inputRefs,
        handleChange,
        handleKeyDown,
        handlePaste,
    } = useOtp({ length, onComplete });

    return (
        <div className={`w-full ${className}`}>
            <div className="flex gap-2 md:gap-3">
                {otp.map((digit, index) => (
                    <input
                        key={index}
                        ref={(el) => { inputRefs.current[index] = el; }}
                        type="text"
                        inputMode="numeric"
                        maxLength={1}
                        value={digit}
                        onChange={(e) => handleChange(index, e.target.value)}
                        onKeyDown={(e) => handleKeyDown(index, e)}
                        onPaste={handlePaste}
                        className="w-1/4 h-12 md:h-14 text-lg md:text-xl font-medium text-center border-2 border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#3E5EF5] focus:border-transparent"
                    />
                ))}
            </div>
        </div>
    );
};

export default OtpInput;