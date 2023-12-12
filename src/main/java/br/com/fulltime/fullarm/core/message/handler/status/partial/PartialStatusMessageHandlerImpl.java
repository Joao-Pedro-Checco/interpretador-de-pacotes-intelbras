package br.com.fulltime.fullarm.core.message.handler.status.partial;

import br.com.fulltime.fullarm.core.message.GenericPackageMessage;
import br.com.fulltime.fullarm.core.message.status.partial.PartialStatusMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PartialStatusMessageHandlerImpl implements PartialStatusMessageHandler {
    private final PartialStatusComponentHandler componentHandler;

    public PartialStatusMessageHandlerImpl(PartialStatusComponentHandler componentHandler) {
        this.componentHandler = componentHandler;
    }

    @Override
    public List<String> handleMessage(GenericPackageMessage genericPackageMessage) {
        List<String> contentList = new ArrayList<>();
        PartialStatusMessage partialStatusMessage = (PartialStatusMessage) genericPackageMessage;
        String messageType = partialStatusMessage.getType().toString();
        String zoneInfo = componentHandler.handleZoneInfo(partialStatusMessage.getZoneInfo());
        String centralModel = componentHandler.handleCentralModel(partialStatusMessage.getCentralModel());
        String firmwareVersion = componentHandler.handleFirmwareVersion(partialStatusMessage.getFirmwareVersion());
        String partitionStatus = componentHandler.handlePartitionStatus(partialStatusMessage.getPartitionStatus());
        String partitions = componentHandler.handlePartitions(partialStatusMessage.getPartitions());
        String centralInfo = componentHandler.handleCentralInfo(partialStatusMessage.getCentralInfo());
        String dateTime = componentHandler.handleDateTime(partialStatusMessage.getDateTime());
        String powerInfo = componentHandler.handlePowerInfo(partialStatusMessage.getPowerInfo());
        String keyboards = componentHandler.handleKeyboards(partialStatusMessage.getKeyboards());
        String receivers = componentHandler.handleReceivers(partialStatusMessage.getReceivers());
        String battery = componentHandler.handleBattery(partialStatusMessage.getBattery());
        String sirenInfo = componentHandler.handleSirenInfo(partialStatusMessage.getSirenInfo());

        boolean phoneLineIsCut = partialStatusMessage.isPhoneLineIsCut();
        boolean failedToCommunicateEvent = partialStatusMessage.isFailedToCommunicateEvent();
        String communicationProblems = componentHandler.handleCommunicationProblems(phoneLineIsCut, failedToCommunicateEvent);

        String pgms = componentHandler.handlePgms(partialStatusMessage.getPgms());

        Collections.addAll(contentList, messageType, zoneInfo, centralModel, firmwareVersion,
                partitionStatus, partitions, centralInfo, dateTime, powerInfo, keyboards, receivers,
                battery, sirenInfo, communicationProblems, pgms);
        return contentList;
    }
}
