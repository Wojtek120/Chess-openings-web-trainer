$(() => {
    const config = {
        draggable: true,
        position: 'start',
        onDragStart: onDragStart,
        onDrop: onDrop,
        onSnapEnd: onSnapEnd
    };

    const board = Chessboard($("#myBoard"), config);
    const game = new Chess();
    let isInTrainingMode = false;
    let movesForTraining = [];


    /**
     * When chess piece is dragged
     */
    function onDragStart(source, piece, position, orientation) {
        if (game.game_over()) return false;

        if ((game.turn() === 'w' && piece.search(/^b/) !== -1) ||
            (game.turn() === 'b' && piece.search(/^w/) !== -1)) {
            return false;
        }
    }

    /**
     * When chess piece is dropped
     */
    function onDrop(source, target) {
        const move = game.move({
            from: source,
            to: target,
            promotion: 'q' //TODO NOTE: always promote to a queen for example simplicity
        });

        if (move === null) return 'snapback';

        $('#fen').html(game.fen());
        $('#pgn').html(game.pgn());

        if(!isInTrainingMode) {
            updatePgnTable(game.pgn());
            updateOpeningTree(game.pgn());
        } else {
            window.setTimeout(makeMove, 250);
        }
    }

    function makeMove () {

    }

    function onSnapEnd () {
        board.position(game.fen())
    }

    const tableWithPgns = $("#pgnTable");
    /**
     * Create table with all moves in current game
     * @param pgnString - string with pgn
     */
    const updatePgnTable = pgnString => {
        const dividedPgn = pgnString.split(" ");

        tableWithPgns.empty();

        let tr = $("<tr></tr>");

        dividedPgn.forEach((value, index) => {
            if (index % 3 === 0) {
                tr = $("<tr></tr>");
                tr.appendTo(tableWithPgns);
                $(`<th class="pgnMove" data-pgn="${value}">${value}</th>`).appendTo(tr);
            } else {
                $(`<td class="pgnMove" data-pgn="${value}">${value}</td>`).appendTo(tr);
            }

        })
    };

    /**
     * When clicked on move from table go to clicked position
     */
    tableWithPgns.on(`click`, e => {
        if (e.target.tagName === 'TD') {
            let pgnString = "";

            tableWithPgns.find(".pgnMove").each(function () {

                pgnString = pgnString + " " + $(this).attr("data-pgn");
                if ($(this).attr("data-pgn") === e.target.getAttribute("data-pgn")) {
                    game.load_pgn(pgnString);
                    board.position(game.fen());
                    return;
                }
            })
        }
    });


    /**
     * Create tree with openings
     */
    let actualOpeningTree = $("#mainTree");
    const updateOpeningTree = pgnString => {
        const dividedPgn = pgnString.split(" ");
        const fullPgn = [...dividedPgn];
        let isFirstInBranch = false;

        if (dividedPgn.length % 3 === 0) {
            dividedPgn.pop();
        } else {
            dividedPgn.pop();
            dividedPgn.pop();
        }

        const lastMovePgn = dividedPgn.join(` `);
        let addButtonFlag = true;
        const openingTree = $("#openingTree");

        //Check if move is alreade in repository
        openingTree.find("button").each((index, element) => {

            if ($(element).attr("data-pgn") === pgnString) {
                addButtonFlag = false;
            }
        });

        //Check to with branch move belongs to or create new branch
        openingTree.find("button").each((index, element) => {

            if (element.getAttribute("data-pgn") === lastMovePgn && $(element).next().is('button') && addButtonFlag) {
                actualOpeningTree = $(`<ul class="nested"></ul>`).appendTo(actualOpeningTree);
                actualOpeningTree = $(`<li></li>`).appendTo(actualOpeningTree);

                isFirstInBranch = true;
            }
        });


        let moveValue = getLastMoveValue(fullPgn, isFirstInBranch);


        if (addButtonFlag) {
            const lastButton = actualOpeningTree.children("button").last();

            if (lastButton.length === 0) {
                $(`<button data-pgn="${pgnString}">${moveValue}</button>`).appendTo(actualOpeningTree);
            } else {
                $(`<button data-pgn="${pgnString}">${moveValue}</button>`).insertAfter(lastButton);
            }

        }
    };

    /**
     * Get value of last move - this text is printed on button
     * @param dividedPgn - png string split(" ")
     * @param firstInBranch
     * @returns {string} - last move value
     */
    const getLastMoveValue = (dividedPgn, firstInBranch) => {
        let moveValue;

        if (dividedPgn.length % 3 === 2) {
            moveValue = dividedPgn[dividedPgn.length - 2] + " " + dividedPgn[dividedPgn.length - 1];
        } else if (dividedPgn.length >= 3 && firstInBranch){
            moveValue = dividedPgn[dividedPgn.length - 3] + "..." + dividedPgn[dividedPgn.length - 1];
        } else {
            moveValue = dividedPgn[dividedPgn.length - 1];
        }

        return moveValue;
    };


    /**
     * Go to position from clicked button from opening tree
     */
    $("#openingTree").on('click', e => {
        if (e.target.tagName === 'BUTTON') {
            const pgnString = e.target.getAttribute("data-pgn");
            game.load_pgn(pgnString);
            board.position(game.fen());

            actualOpeningTree = $(e.target).closest("li");

            updatePgnTable(pgnString);
        }
    });


    /**
     * Create json from tree opening
     */
    const getJsonFromTreeOpening = () => {

        let arrayToBeConverted = {};
        let actualBranch = 0;
        let parentBranchNumber = -1;

        arrayToBeConverted = addBranchToArray(actualBranch, parentBranchNumber, $("#mainTree"), arrayToBeConverted);

        return JSON.stringify({...arrayToBeConverted});
    };

    /**
     * Recursive call function to add nested branches
     * @param actualBranch
     * @param parentBranchNumber
     * @param branchLi
     * @param arrayToAddBranchTo
     */
    const addBranchToArray = (actualBranch, parentBranchNumber, branchLi, arrayToAddBranchTo) => {

        let nextBranchNumber = actualBranch;

        $(branchLi).children().each((index, element) => {

            console.log(arrayToAddBranchTo);

            if (element.tagName === "BUTTON") {
                arrayToAddBranchTo[`branch.${actualBranch}.${parentBranchNumber}.${index}`] = $(element).data("pgn");
            } else {
                let nextBranches = $(element).children("li");
                if (nextBranches.length > 0) {

                    nextBranches.each((index1, element1) => {
                        nextBranchNumber++;

                        arrayToAddBranchTo = addBranchToArray(nextBranchNumber, actualBranch, element1, arrayToAddBranchTo);
                    });
                }
            }
        });

        return arrayToAddBranchTo;
    };


    /**
     * When button clicked set ajax connection
     */
    $("#saveTreeToDatabase").on('click', () => {
        const json = getJsonFromTreeOpening();
        console.log(json);


        $.ajax({
            url: '/chessboard/save/repository',
            data: json,
            contentType: "application/json",
            method: "POST",
            headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
        })

    });


    $(function () {
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });


    /**
     * Fip board on click
     */
    $("#changeOrientationBtn").on('click', board.flip);


    /**
     * On click load opening branches from database
     */
    $(".loadRepository").on('click', () => {

        $.ajax({
            url: '/chessboard/branch/load/1',
            contentType: "application/json",
            method: "GET",
            success: function (data) {

                let json = JSON.parse(data);
                createTree(json);

            }
        })

    });


    /**
     * Create tree from json
     */
    const createTree = treeJson => {

        console.log(Object.keys(treeJson));

        let actualOpeningTree = $("#mainTree");
        actualOpeningTree.data("branch", 0);
        actualOpeningTree.data("parent", -1);

        let previousBranch = 0;
        let previousParentBranch = -1;


        $(Object.keys(treeJson)).each((index, el) => {

            const myRegexp = /branch.(\d+).(-?\d+).(\d+)/;
            const numbers = myRegexp.exec(el);

            const branchNumber = parseInt(numbers[1], 10);
            const parentNumber = parseInt(numbers[2], 10);
            const moveNumber = parseInt(numbers[3], 10);
            const pgnStr = treeJson[el];

            let moveValue = getLastMoveValue(pgnStr.split(" "), true);

            console.log(moveValue);

            if (previousBranch === branchNumber && previousParentBranch === parentNumber) {
                moveValue = getLastMoveValue(pgnStr.split(" "), false);
                $(`<button data-pgn="${pgnStr}">${moveValue}</button>`).appendTo(actualOpeningTree);

            } else if (previousBranch === parentNumber) {


                actualOpeningTree = $(`<ul class="nested"></ul>`).appendTo(actualOpeningTree);
                actualOpeningTree = $(`<li data-branch="${branchNumber}" data-parent="${parentNumber}"></li>`).appendTo(actualOpeningTree);
                $(`<button data-pgn="${pgnStr}">${moveValue}</button>`).appendTo(actualOpeningTree);

            } else if (previousParentBranch === parentNumber) {

                actualOpeningTree = $(`<ul class="nested"></ul>`).insertAfter(actualOpeningTree.closest("li"));
                actualOpeningTree = $(`<li data-branch="${branchNumber}" data-parent="${parentNumber}"></li>`).appendTo(actualOpeningTree);
                $(`<button data-pgn="${pgnStr}">${moveValue}</button>`).appendTo(actualOpeningTree);

            }else {
                let branchToAppendToNumber = -1;

                while (branchToAppendToNumber !== parentNumber) {
                    actualOpeningTree = actualOpeningTree.parents("li").first();
                    branchToAppendToNumber = actualOpeningTree.data("branch");
                }

                actualOpeningTree = $(`<ul class="nested"></ul>`).appendTo(actualOpeningTree.closest("li"));
                actualOpeningTree = $(`<li data-branch="${branchNumber}" data-parent="${parentNumber}"></li>`).appendTo(actualOpeningTree);
                $(`<button data-pgn="${pgnStr}">${moveValue}</button>`).appendTo(actualOpeningTree);

            }

            previousBranch = branchNumber;
            previousParentBranch = parentNumber;
        })
    };



    $("#trainBtn").on('click', e => {

        $("#mainTree").find("button").each((index, element) => {
            console.log($(element).data("pgn"));
            movesForTraining.push($(element).data("pgn"));
        })
    });


















    var $status = $('#status')
    var $fen = $('#fen')
    var $pgn = $('#pgn')

// update the board position after the piece snap
// for castling, en passant, pawn promotion
    function onSnapEnd() {
        board.position(game.fen())
    }

    function updateStatus() {
        var status = ''

        var moveColor = 'White'
        if (game.turn() === 'b') {
            moveColor = 'Black'
        }

        // checkmate?
        if (game.in_checkmate()) {
            status = 'Game over, ' + moveColor + ' is in checkmate.'
        }

        // draw?
        else if (game.in_draw()) {
            status = 'Game over, drawn position'
        }

        // game still on
        else {
            status = moveColor + ' to move'

            // check?
            if (game.in_check()) {
                status += ', ' + moveColor + ' is in check'
            }
        }

        $status.html(status);
        $fen.html(game.fen());
        $pgn.html(game.pgn());
    }

    updateStatus();


});