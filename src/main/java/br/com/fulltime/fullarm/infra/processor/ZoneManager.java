package br.com.fulltime.fullarm.infra.processor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Service
public class ZoneManager {
    public List<Integer> parseZonesWithBitOn1(List<String> bytes) {
        int offset = 1;
        List<Integer> zonesWithBitOn1 = new ArrayList<>();
        for (String aByte : bytes) {
            BitSet zones = BitManager.convertHexByteToBitSet(aByte);
            zonesWithBitOn1.addAll(parseOffsetZones(zones, offset));
            offset += 8;
        }

        return zonesWithBitOn1;
    }

    private List<Integer> parseOffsetZones(BitSet zones, int offset) {
        int index = 0;
        List<Integer> offsetZones = new ArrayList<>();
        while (zones.nextSetBit(index) > -1) {
            if (zones.get(index)) offsetZones.add(index + offset);
            index++;
        }

        return offsetZones;
    }
}
