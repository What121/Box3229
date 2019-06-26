package com.bestom.boxmod.common.utils;

/**
 * CRC8计算
 */
public class CRC8 {

    /**
     * 计算数组的CRC8校验值
     *
     * @param hex 需要计算的16进制字符串
     * @return CRC8校验值
     */
    public static String calcCrc8(String hex) {
        int crc = calcCrc8(DataTurn.HexToByteArr(hex));
        return DataTurn.IntToHex(crc);
    }

    /**
     * 计算数组的CRC8校验值
     *
     * @param data 需要计算的数组
     * @return CRC8校验值
     */
    public static int calcCrc8(byte[] data) {
        int crc = 0;
        int genPoly = 0x07;
        for (int i = 0; i < data.length; i++) {
            crc ^= data[i];
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x80) != 0) {
                    crc = (crc << 1) ^ genPoly;
                } else {
                    crc <<= 1;
                }
            }
        }
        crc &= 0xff;//保证CRC余码输出为2字节。
        return crc;
    }
}
